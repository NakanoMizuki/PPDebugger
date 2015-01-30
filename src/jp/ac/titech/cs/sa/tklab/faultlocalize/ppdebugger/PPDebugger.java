package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.bind.JAXBException;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.out.IOut;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.NameCreator;
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
	private int numFailed;
	
	
	public PPDebugger(int hopNum){
		this.hopNum = Math.max(hopNum,0);
		passedModel = new LearnedModel();
		failedModel = new LearnedModel();
	}
	
	public void learn(File[] passedFiles,File[] failedFiles) throws JAXBException{
		numFailed = failedFiles.length;
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
			if(NameCreator.isParam(sd.getSourcePath())) continue;
			double prob;
			if(failedSt.getExecutedCount() != numFailed){		//通過しない失敗実行があった
				prob =-1.0;
			}else{
				StatementState passedSt = passedModel.getStatementState(sd);
				if(passedSt == null){		//成功実行で一度も通過していない
					prob = 1.0;
				}else{
					prob = 0;
					for(DataDependencySet dds: failedSt.getDataDependencySets()){
						double current = failedSt.getProb(dds) / (failedSt.getProb(dds) + passedSt.getProb(dds));
						prob = Math.max(prob, current);
					}
				}
			}
			StatementProb stpb = new StatementProb(sd, prob);
			list.add(stpb);
		}
		return new Result(list);
	}


	public void printLearnedModel(IOut out) {
		out.println("-------passed Model-------");
		passedModel.printAllState(out);
		out.println("----------");
		out.println("");
		out.println("-------failed Model-------");
		failedModel.printAllState(out);
		out.println("----------");
		out.println("");
	}
}
