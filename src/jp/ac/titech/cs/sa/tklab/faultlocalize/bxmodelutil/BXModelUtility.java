package jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.CatchEnd;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.CatchStart;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ConditionEnd;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ConditionStart;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ConstructorEntry;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ConstructorExit;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.DummyEvent;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ExceptionOccurrence;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.FinallyEnd;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.FinallyStart;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.LoopEnd;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.LoopStart;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Looping;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodEntry;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodExit;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Node;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.TryEnd;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.TryStart;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableDefinition;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableReference;

/**
 * 
 * @author ?
 *
 */
public class BXModelUtility{

	// Nodeの種類を判別する
	// Nodeクラスはいじりたくないので、Utilityクラスにある
	public static NodeKind getNodeKind(Node node) {
		NodeKind nodeKind;

		if (node.getMethodEntry() != null) {
			nodeKind = NodeKind.METHOD_ENTRY;
		} else if (node.getMethodExit() != null) {
			nodeKind = NodeKind.METHOD_EXIT;
		} else if (node.getConstructorEntry() != null) {
			nodeKind = NodeKind.CONSTRUCTOR_ENTRY;
		} else if (node.getConstructorExit() != null) {
			nodeKind = NodeKind.CONSTRUCTOR_EXIT;
		} else if (node.getConditionStart() != null) {
			nodeKind = NodeKind.CONDITION_START;
		} else if (node.getConditionEnd() != null) {
			nodeKind = NodeKind.CONDITION_END;
		} else if (node.getLoopStart() != null) {
			nodeKind = NodeKind.LOOP_START;
		} else if (node.getLooping() != null) {
			nodeKind = NodeKind.LOOPING;
		} else if (node.getLoopEnd() != null) {
			nodeKind = NodeKind.LOOP_END;
		} else if (node.getTryStart() != null) {
			nodeKind = NodeKind.TRY_START;
		} else if (node.getTryEnd() != null) {
			nodeKind = NodeKind.TRY_END;
		} else if (node.getCatchStart() != null) {
			nodeKind = NodeKind.CATCH_START;
		} else if (node.getCatchEnd() != null) {
			nodeKind = NodeKind.CATCH_END;
		} else if (node.getFinallyStart() != null) {
			nodeKind = NodeKind.FINALLY_START;
		} else if (node.getFinallyEnd() != null) {
			nodeKind = NodeKind.FINALLY_END;
		} else if (node.getVariableDefinition() != null) {
			nodeKind = NodeKind.VARIABLE_DEFINITION;
		} else if (node.getVariableReference() != null) {
			nodeKind = NodeKind.VARIABLE_REFERENCE;
		} else if (node.getExceptionOccurrence() != null) {
			nodeKind = NodeKind.EXCEPTION_OCCURRENCE;
		} else if (node.getDummyEvent() != null) {
			nodeKind = NodeKind.DUMMY;
		} else {
			throw new IllegalArgumentException();
		}

		return nodeKind;
	}


	// Nodeのイベントシグネチャを返す
	public static EventSignature getEventSignature(Node node){
		EventSignature es;

		if (node.getMethodEntry() != null) {
			MethodEntry temp = node.getMethodEntry();
			es = new EventSignature(temp.getThread(), temp.getCalleeLineNumber(), temp.getCalleeSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getMethodExit() != null) {
			MethodExit temp = node.getMethodExit();
			es = new EventSignature(temp.getThread(), temp.getCalleeLineNumber(), temp.getCalleeSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getConstructorEntry() != null) {
			ConstructorEntry temp = node.getConstructorEntry();
			es = new EventSignature(temp.getThread(), temp.getCalleeLineNumber(), temp.getCalleeSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getConstructorExit() != null) {
			ConstructorExit temp = node.getConstructorExit();
			es = new EventSignature(temp.getThread(), temp.getCalleeLineNumber(), temp.getCalleeSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getConditionStart() != null) {
			ConditionStart temp = node.getConditionStart();
			es = new EventSignature(temp.getThread(), temp.getLineNumber(), temp.getSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getConditionEnd() != null) {
			ConditionEnd temp = node.getConditionEnd();
			es = new EventSignature(temp.getThread(), temp.getLineNumber(), temp.getSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getLoopStart() != null) {
			LoopStart temp = node.getLoopStart();
			es = new EventSignature(temp.getThread(), temp.getLineNumber(), temp.getSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getLooping() != null) {
			Looping temp = node.getLooping();
			es = new EventSignature(temp.getThread(), temp.getLineNumber(), temp.getSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getLoopEnd() != null) {
			LoopEnd temp = node.getLoopEnd();
			es = new EventSignature(temp.getThread(), temp.getLineNumber(), temp.getSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getTryStart() != null) {
			TryStart temp = node.getTryStart();
			es = new EventSignature(temp.getThread(), temp.getLineNumber(), temp.getSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getTryEnd() != null) {
			TryEnd temp = node.getTryEnd();
			es = new EventSignature(temp.getThread(), temp.getLineNumber(), temp.getSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getCatchStart() != null) {
			CatchStart temp = node.getCatchStart();
			es = new EventSignature(temp.getThread(), temp.getLineNumber(), temp.getSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getCatchEnd() != null) {
			CatchEnd temp = node.getCatchEnd();
			es = new EventSignature(temp.getThread(), temp.getLineNumber(), temp.getSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getFinallyStart() != null) {
			FinallyStart temp = node.getFinallyStart();
			es = new EventSignature(temp.getThread(), temp.getLineNumber(), temp.getSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getFinallyEnd() != null) {
			FinallyEnd temp = node.getFinallyEnd();
			es = new EventSignature(temp.getThread(), temp.getLineNumber(), temp.getSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getVariableDefinition() != null) {
			VariableDefinition temp = node.getVariableDefinition();
			es = new EventSignature(temp.getThread(), temp.getLineNumber(), temp.getSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getVariableReference() != null) {
			VariableReference temp = node.getVariableReference();
			es = new EventSignature(temp.getThread(), temp.getLineNumber(), temp.getSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getExceptionOccurrence() != null) {
			ExceptionOccurrence temp = node.getExceptionOccurrence();
			es = new EventSignature(temp.getThread(), temp.getLineNumber(), temp.getSourcePath(), temp.getEventNumber());
			return es;
		}
		if (node.getDummyEvent() != null) {
			DummyEvent temp = node.getDummyEvent();
			es = new EventSignature(temp.getThread(), temp.getLineNumber(), temp.getSourcePath(), temp.getEventNumber());
			return es;
		}
		throw new IllegalArgumentException();
	}
}
