package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.learned;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.out.IOut;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Statement;

public class LearnedModel {
	private int numCase;
	private List<StatementState> statementStates;
	
	public LearnedModel(){
		numCase = 0;
		statementStates = new ArrayList<StatementState>();
	}
	
	public void init(){
		numCase = 0;
		statementStates.clear();
	}
	
	public synchronized void merge(ExecutionModel em){
		++ numCase;
		List<Statement> statements = em.getStatements();
		for(Statement st : statements){
			StatementState stState = this.getStatementState(st.getStatementData());
			if(stState == null){
				stState = new StatementState(st.getStatementData());
				stState.addCount();
				stState.addStatement(st);
				statementStates.add(stState);
			}else{
				stState.addCount();
				stState.addStatement(st);
			}
		}
	}
	
	public List<StatementState> getStatementStates(){
		return statementStates;
	}
	
	public StatementState getStatementState(StatementData sd){
		for(StatementState stst : statementStates){
			if(stst.getStatementData().equals(sd)){
				return stst;
			}
		}
		return null;
	}
	public StatementState getStatementState(Statement st){
		return getStatementState(st.getStatementData());
	}
	
	
	public void printAllState(IOut out){
		Collections.sort(statementStates);
		
		out.println("Passed Model (learned from " + numCase + "files)  ----------");
		for(StatementState ss : statementStates){
			ss.printState(out);
		}
		out.println("----------");
		out.print("");
	}

}
