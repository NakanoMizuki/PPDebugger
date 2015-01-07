package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	private final int NUM_THREAD = 8;
	private final ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREAD);
	
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
			executorService.submit(new Executor(passedModel, file, hopNum));
		}
		executorService.shutdown();
		while(!executorService.isTerminated()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("PPDebugger ended learning.");
	}
	
	public List<Result> createResults(File[] failedFailes) throws JAXBException{
		List<Result> results = new ArrayList<Result>();
		for(File file:failedFailes){
			ExecutionModel em = creator.createExecutionModel(file,hopNum);
			Result result =  passedModel.createResults(em,file.getName());
			results.add(result);
		}
		
		return results;
	}

	public void printAllRanking(IOut out) {
		passedModel.printAllState(out);
	}
}
