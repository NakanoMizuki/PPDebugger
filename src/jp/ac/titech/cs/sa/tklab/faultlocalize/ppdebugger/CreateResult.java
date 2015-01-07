package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger;

import java.io.File;
import java.util.concurrent.Callable;

import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.Creator;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.pass.PassedModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result.Result;

public class CreateResult implements Callable<Result>{
	private final PassedModel passedModel;
	private final File failedFile;
	private final int hopNum;
	
	public CreateResult(PassedModel passedModel,File failedFile,int hopNum) {
		this.passedModel = passedModel;
		this.failedFile = failedFile;
		this.hopNum = hopNum;
	}
	
	
	@Override
	public Result call() throws Exception {
		ExecutionModel em = new Creator().createExecutionModel(failedFile,hopNum);
		return passedModel.createResults(em,failedFile.getName());
	}
	
}
