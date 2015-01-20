package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;

import java.io.File;
import java.util.Stack;

import javax.xml.bind.JAXBException;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Node;
import jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil.BPDGHolder;
import jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil.BXModelUtility;
import jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil.NodeKind;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.postCreate.Compressor;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.postCreate.Propagator;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.scope.Scope;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.LineVariable;

public class Creator {
	private ExecutionModel model;
	private Stack<LineVariable> argsStack;
	private Stack<LineVariable> returnStack;
	private Scope scope;
	
	public Creator(){
		model = new ExecutionModel();
		argsStack = new Stack<LineVariable>();
		returnStack = new Stack<LineVariable>();
		scope = new Scope();
	}
	
	public ExecutionModel createExecutionModel(File file,int hopNum) throws JAXBException{
		BPDGHolder holder = new BPDGHolder(file);
		Node node;
		while((node = holder.getNextNode()) != null){
			createDataDependency(node);
		}
		file = null;
		holder = null;
		argsStack.clear();
		returnStack.clear();
		scope.clear();
		
		//データ依存を伝搬させる
		Propagator.propagate(model, hopNum);
		Compressor.compress(model);

		return model;
	}
	
	private void createDataDependency(Node node){
		NodeKind kind = BXModelUtility.getNodeKind(node);
		LineVariable argsLine;
		LineVariable refLine;
		switch(kind){
		case VARIABLE_REFERENCE:
			VariableReferenceVisitor.create(model,node.getVariableReference(),scope,returnStack.peek(),argsStack.peek());
			break;
		case VARIABLE_DEFINITION:
			VariableDefinitionVisitor.create(model, node.getVariableDefinition(),scope);
			break;
		case METHOD_ENTRY:
			scope.entry(NameCreator.createMethodName(node.getMethodEntry()));
			if(!argsStack.empty()){		//メインメソッドはempty
				argsLine = argsStack.peek();
				MethodEntryVisitor.create(model,node.getMethodEntry(),argsLine,scope);
			}
			argsStack.push(new LineVariable());
			returnStack.push(new LineVariable());
			break;
		case METHOD_EXIT:
			refLine = returnStack.pop();
			argsStack.pop();
			if(!returnStack.isEmpty()){	//メインメソッドの終了時はスタックが空になる。このときはデータ依存を考慮する必要はない
				MethodExitVisitor.create(model,node.getMethodExit(),refLine,returnStack.peek(),argsStack.peek(),scope);
			}
			scope.exit();
			break;
		case CONSTRUCTOR_ENTRY:
			scope.entry(NameCreator.createConstructorName(node.getConstructorEntry()));
			ConstructorEntryVisitor.create(model,node.getConstructorEntry(),argsStack.peek(),scope);
			argsStack.push(new LineVariable());
			returnStack.push(new LineVariable());
			break;
		case CONSTRUCTOR_EXIT:
			argsStack.pop();
			returnStack.pop();
			ConstructorExitVisitor.create(model, node.getConstructorExit());
			scope.exit();
			break;
		default:
			break;
		}
	}
	
}
