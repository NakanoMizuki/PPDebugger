package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.bind.JAXBException;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.out.IOut;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.learned.LearnedModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.learned.StatementState;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result.Result;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result.StatementProb;

/**
 * ツールPPDebuggerのクラス
 * @author nakano
 *
 */
public class PPDebugger{
	private final int NUM_THREAD = 8;
	
	private final int hopNum;
	private final LearnedModel passedModel;
	private final LearnedModel failedModel;
	
	
	public PPDebugger(int hopNum){
		this.hopNum = Math.max(hopNum,0);
		passedModel = new LearnedModel();
		failedModel = new LearnedModel();
	}
	
	public void learn(File[] passedFiles,File[] failedFiles) throws JAXBException{
		learn(passedModel, passedFiles);
		learn(failedModel, failedFiles);
	}
	
	private void learn(LearnedModel learnedModel,File[] files)throws JAXBException {
		learnedModel.init();
		System.out.println("PPDebugger starts learning. (" + files.length + "Files) hop=" + hopNum);
		
		//学習(複数スレッド)
		ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREAD);
		for(File file : files){
			executorService.submit(new Executor(learnedModel,file,hopNum));
			file = null;
		}
		files = null;
		
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
	
	public Result createResult(){
		//結果の取得
		List<StatementProb> list = new ArrayList<StatementProb>();
		for(StatementState failedSt :failedModel.getStatementStates()){
			StatementData sd = failedSt.getStatementData();
			StatementState passedSt = passedModel.getStatementState(sd);
			double prob;
			if(passedSt == null){
				prob = 1;
			}else{
				prob = 0;
				for(DataDependencySet dds: failedSt.getDataDependencySets()){
					double difference = failedSt.getProb(dds) - passedSt.getProb(dds);
					prob = Math.max(prob, difference);
				}
			}
			StatementProb stpb = new StatementProb(sd, prob);
			list.add(stpb);
		}
		return new Result(list);
	}
	
	public int createScore(Result result,List<StatementData> faults){
		int score = result.calcScore(faults);
		return score;
	}

	public void printAllRanking(IOut out) {
		passedModel.printAllState(out);
	}
}
