package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.bind.JAXBException;

import jp.ac.titech.cs.sa.tklab.faultlocalize.out.IOut;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.pass.PassedModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result.Result;

/**
 * ツールPPDebuggerのクラス
 * @author nakano
 *
 */
public class PPDebugger{
	private final int NUM_THREAD = 8;
	
	private final int hopNum;
	private final PassedModel passedModel;
	
	
	public PPDebugger(int hopNum){
		this.hopNum = Math.max(hopNum,0);
		passedModel = new PassedModel();
	}
	
	
	public void learn(File[] passedFiles)throws JAXBException {
		passedModel.init();
		System.out.println("PPDebugger starts learning. (" + passedFiles.length + "passedFiles) hop=" + hopNum);
		
		//成功実行の学習(複数スレッド)
		ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREAD);
		for(File file : passedFiles){
			executorService.submit(new Executor(passedModel,file,hopNum));
			file = null;
		}
		passedFiles = null;
		
		//すべて終わるまで待つ
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
		ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREAD);
		List<Future<Result>> futures = new ArrayList<Future<Result>>();
		for(File file:failedFailes){
			futures.add(executorService.submit(new CreateResult(passedModel,file,hopNum)));
			file = null;
		}
		failedFailes = null;
		executorService.shutdown();
		
		//結果の取得
		List<Result> results = new ArrayList<Result>();
		for(Future<Result> future : futures){
			try {
				results.add(future.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return results;
	}

	public void printAllRanking(IOut out) {
		passedModel.printAllState(out);
	}
}
