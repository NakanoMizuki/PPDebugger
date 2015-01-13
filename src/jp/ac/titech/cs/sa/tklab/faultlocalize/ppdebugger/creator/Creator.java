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
	private Stack<LineVariable> lineStack;
	private Scope scope;
	private StatementDataFactory factory;
	
	public Creator(StatementDataFactory factory){
		model = new ExecutionModel();
		lineStack = new Stack<LineVariable>();
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
		lineStack.clear();
		scope.clear();
		
		//データ依存を伝搬させる
		Propagator.propagate(model, hopNum);
		Compressor.compress(model);

		return model;
	}
	
	private void createDataDependency(Node node){
		NodeKind kind = BXModelUtility.getNodeKind(node);
		LineVariable lineVar;
		switch(kind){
		case VARIABLE_REFERENCE:
			VariableReferenceCreator.create(model,node.getVariableReference(),scope,lineStack.peek(),factory);
			break;
		case VARIABLE_DEFINITION:
			VariableDefinitionCreator.create(model, node.getVariableDefinition(),scope,factory);
			break;
		case METHOD_ENTRY:
			scope.entry(NameCreator.createMethodName(node.getMethodEntry()));
			if(!lineStack.empty()){		//メインメソッドはempty
				lineVar = lineStack.peek();
				MethodEntryCreator.create(model,node.getMethodEntry(),lineVar,factory);
			}
			lineStack.push(new LineVariable());
			break;
		case METHOD_EXIT:
			lineVar = lineStack.pop();
			scope.exit();
			MethodExitCreator.create(model,node.getMethodExit(),lineVar,factory);
			break;
		case CONSTRUCTOR_ENTRY:
			lineStack.push(new LineVariable());
			scope.entry(NameCreator.createMethodName(node.getConstructorEntry()));
			ConstructorEntryCreator.create(model,node.getConstructorEntry());
			break;
		case CONSTRUCTOR_EXIT:
			lineVar = lineStack.pop();
			scope.exit();
			ConstructorExitCreator.create(model, node.getConstructorExit());
			break;
		default:
			break;
		}
	}
	
}
