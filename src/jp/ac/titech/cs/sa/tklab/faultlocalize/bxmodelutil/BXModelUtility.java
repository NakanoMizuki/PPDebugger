package jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Node;

public class BXModelUtility{

	// Nodeの種類を判別する
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

}
