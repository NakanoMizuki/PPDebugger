package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;

import java.io.File;
import java.util.Stack;

import javax.xml.bind.JAXBException;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Node;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementDataFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil.BPDGHolder;
import jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil.BXModelUtility;
import jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil.NodeKind;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.postCreate.Compressor;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.postCreate.Propagator;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.LineVariable;

public class Creator {
	private ExecutionModel model;
	private Stack<LineVariable> refStack;
	private Stack<LineVariable> argsStack;
	private Scope scope;
	private StatementDataFactory factory;
	
	public Creator(StatementDataFactory factory){
		model = new ExecutionModel();
		refStack = new Stack<LineVariable>();
		argsStack = new Stack<LineVariable>();
		scope = new Scope();
		this.factory = factory;
	}
	
	public ExecutionModel createExecutionModel(File file,int hopNum) throws JAXBException{
		BPDGHolder holder = new BPDGHolder(file);
		Node node;
		while((node = holder.getNextNode()) != null){
			createDataDependency(node);
		}
		file = null;
		holder = null;
		refStack.clear();
		argsStack.clear();
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
			VariableReferenceVisitor.create(model,node.getVariableReference(),scope,refStack.peek(),argsStack.peek(),factory);
			break;
		case VARIABLE_DEFINITION:
			VariableDefinitionVisitor.create(model, node.getVariableDefinition(),scope,factory);
			break;
		case METHOD_ENTRY:
			scope.entry(NameCreator.createMethodName(node.getMethodEntry()));
			if(!argsStack.empty()){		//メインメソッドはempty
				argsLine = argsStack.peek();
				MethodEntryVisitor.create(model,node.getMethodEntry(),argsLine,scope);
			}
			refStack.push(new LineVariable());
			argsStack.push(new LineVariable());
			break;
		case METHOD_EXIT:
			refLine = refStack.pop();
			argsStack.pop();
			scope.exit();
			if(!refStack.isEmpty()){	//メインメソッドの終了時はスタックが空になる。このときはデータ依存を考慮する必要はない
				MethodExitVisitor.create(model,node.getMethodExit(),refLine,refStack.peek(),argsStack.peek(),factory);
			}
			break;
		case CONSTRUCTOR_ENTRY:
			refStack.push(new LineVariable());
			argsStack.push(new LineVariable());
			scope.entry(NameCreator.createMethodName(node.getConstructorEntry()));
			ConstructorEntryVisitor.create(model,node.getConstructorEntry());
			break;
		case CONSTRUCTOR_EXIT:
			refStack.pop();
			argsStack.pop();
			scope.exit();
			ConstructorExitVisitor.create(model, node.getConstructorExit());
			break;
		default:
			break;
		}
	}
	
}
