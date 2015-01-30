package jp.ac.titech.cs.sa.tklab.faultlocalize.tarantula;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;



import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.Valuation;
import jp.ac.titech.cs.sa.tklab.faultlocalize.out.IOut;

/**
 * 
 * @author nakano
 *
 */
public class Tarantula {
	private final TStatementHolder tsHolder;
	
	public Tarantula(){
		tsHolder = new TStatementHolder();
	}

	public void learn(File[] passedFiles, File[] failedFiles) throws IOException{
		System.out.println("Tarantula starts."
						+ " (" + passedFiles.length + "passedFiles," + failedFiles.length + "failedFiles)");
		
		tsHolder.init();
		//成功実行を学習
		for(File file : passedFiles){
			Set<StatementData> sdSet = TraceReader.createSDSet(file);
			for(StatementData sd : sdSet){
				tsHolder.addPassedStatement(new TStatement(sd));
			}
		}
		//失敗実行を学習
		for(File file : failedFiles){
			Set<StatementData> sdSet = TraceReader.createSDSet(file);
			for(StatementData sd : sdSet){
				tsHolder.addFailedStatement(new TStatement(sd));
			}
		}

		//全てのステートメントのsuspiciousを計算
		for(TStatement tst:tsHolder.getList()){
			tst.calcSuspicious(passedFiles.length, failedFiles.length);
			//tst.calcOchiai(passedFiles.length, failedFiles.length);
			//tst.calcER5b(passedFiles.length,failedFiles.length);
		}
		tsHolder.sort();
		
		System.out.println("Tarantula ended.");
	}


	public void printRanking(IOut out,int num) {
		List<TStatement> tsList = tsHolder.getList();
		int tmp = (num > tsList.size()) ? tsList.size() : num;
		for(int i=0; i < tmp; i++){
			out.println("\t" + (i+1) + ":\t" + tsList.get(i).toString());
		}
	}

	public void printAllRanking(IOut out) {
		printRanking(out,tsHolder.getList().size());
	}
	
	public Valuation getValuation(List<StatementData> faults){
		return tsHolder.getValuation(faults);
	}

}
