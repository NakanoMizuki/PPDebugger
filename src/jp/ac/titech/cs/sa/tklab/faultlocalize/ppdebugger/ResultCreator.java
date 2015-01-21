package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.Creator;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Statement;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.pass.PassedModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.pass.StatementState;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result.Result;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result.StatementProb;

public class ResultCreator implements Callable<Result>{
	private final PassedModel passedModel;
	private final File failedFile;
	private final int hopNum;
	
	
	public ResultCreator(PassedModel passedModel,File failedFile,int hopNum) {
		this.passedModel = passedModel;
		this.failedFile = failedFile;
		this.hopNum = hopNum;
	}
	
	
	@Override
	public Result call() throws Exception {
		ExecutionModel em = new Creator().createExecutionModel(failedFile,hopNum);
		return createResult(passedModel,em,failedFile.getName());
	}
	
	private Result createResult(PassedModel passedModel,ExecutionModel executionModel,String fileName){
		int numCase = passedModel.getNumCase();
		Set<StatementProb> stPbs = new HashSet<StatementProb>();
		for(Statement statement :executionModel.getStatements()){
			if(statement.getStatementData().getSourcePath().charAt(0) == '*') continue;
			if(statement.getDataDependencySets().isEmpty()){
				stPbs.add(new StatementProb(statement.getStatementData(), 1D));
				continue;
			}
			int minNum = numCase;
			for(DataDependencySet dds : statement.getDataDependencySets()){
				StatementState stState = passedModel.getStatementState(statement);
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
}
