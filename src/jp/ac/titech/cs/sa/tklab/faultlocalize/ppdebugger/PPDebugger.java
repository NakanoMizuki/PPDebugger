package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger;

import java.io.File;

import javax.xml.bind.JAXBException;

import jp.ac.titech.cs.sa.tklab.faultlocalize.out.IOut;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.Creator;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.pass.PassedModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result.Result;

/**
 * ツールPPDebuggerのクラス
 * @author nakano
 *
 */
public class PPDebugger{
	private final int hopNum;
	private final PassedModel passedModel;
	private final Creator creator;
	
	
	public PPDebugger(int hopNum){
		this.hopNum = Math.max(hopNum,0);
		passedModel = new PassedModel();
		creator = new Creator();
	}
	
	
	public void learn(File[] passedFiles)throws JAXBException {
		passedModel.init();
		System.out.println("PPDebugger starts learning. (" + passedFiles.length + "passedFiles) hop=" + hopNum);
		//成功実行の学習
		for(File file : passedFiles){
			ExecutionModel em = creator.createExecutionModel(file,hopNum);
			passedModel.merge(em);
		}
		System.out.println("PPDebugger ended learning.");
	}
	
	public Result check(File failedFile) throws JAXBException{
		ExecutionModel em = creator.createExecutionModel(failedFile,hopNum);
		return passedModel.createResults(em,failedFile.getName());
	}

	public void printAllRanking(IOut out) {
		passedModel.printAllState(out);
	}
}
