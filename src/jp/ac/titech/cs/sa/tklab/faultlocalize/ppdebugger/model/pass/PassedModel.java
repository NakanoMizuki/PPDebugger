package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.pass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.out.IOut;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Statement;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result.Result;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result.StatementProb;

public class PassedModel {
	private int numCase;
	private List<StatementState> statementStates;
	
	public PassedModel(){
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
				stState.addStatement(st);
				statementStates.add(stState);
			}else{
				stState.addStatement(st);
			}
		}
	}
	
	private StatementState getStatementState(StatementData sd){
		for(StatementState stst : statementStates){
			if(stst.getStatementData().equals(sd)){
				return stst;
			}
		}
		return null;
	}
	private StatementState getStatementState(Statement st){
		return getStatementState(st.getStatementData());
	}
	
	public Result createResults(ExecutionModel executionModel,String fileName){
		Set<StatementProb> stPbs = new HashSet<StatementProb>();
		for(Statement statement :executionModel.getStatements()){
			if(statement.getDataDependencySets().isEmpty()){
				stPbs.add(new StatementProb(statement.getStatementData(), 1D));
			}
			int minNum = numCase;
			for(DataDependencySet dds : statement.getDataDependencySets()){
				StatementState stState = getStatementState(statement);
				if(stState == null) {
					minNum = 0;
					break;
				}
				minNum = Math.min(minNum, stState.getNum(dds));
			}
			StatementProb stPb;
			if(minNum == 0){
				stPb = new StatementProb(statement.getStatementData(),0D);
			}else{
				stPb = new StatementProb(statement.getStatementData(),(double)minNum/numCase);
			}
			stPbs.add(stPb);
		}
		
		return new Result(fileName,new ArrayList<StatementProb>(stPbs));
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
