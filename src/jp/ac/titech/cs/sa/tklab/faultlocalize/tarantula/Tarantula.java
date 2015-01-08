package jp.ac.titech.cs.sa.tklab.faultlocalize.tarantula;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBException;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Node;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil.BPDGHolder;
import jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil.BXModelUtility;
import jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil.EventSignature;
import jp.ac.titech.cs.sa.tklab.faultlocalize.out.IOut;

/**
 * 
 * @author nakano
 *
 */
public class Tarantula {
	TStatementHolder tsHolder;
	
	public Tarantula(){
		tsHolder = new TStatementHolder();
	}

	public void learn(File[] passedFiles, File[] failedFiles) throws JAXBException{
		System.out.println("Tarantula starts learning."
						+ " (" + passedFiles.length + "passedFiles," + failedFiles.length + "failedFiles)");
		
		tsHolder.init();
		//成功実行を学習
		for(File f : passedFiles){
			BPDGHolder holder = new BPDGHolder(f);
			Set<StatementData> sdSet = createSDSet(holder);
			for(StatementData sd : sdSet){
				tsHolder.addPassedStatement(new TStatement(sd));
			}
		}
		//失敗実行を学習
		for(File f : failedFiles){
			BPDGHolder holder = new BPDGHolder(f);
			Set<StatementData> sdSet = createSDSet(holder);
			for(StatementData sd : sdSet){
				tsHolder.addFailedStatement(new TStatement(sd));
			}
		}

		//全てのステートメントのsuspiciousを計算
		for(TStatement tst:tsHolder.getList()){
			tst.calcSuspicious(passedFiles.length, failedFiles.length);
		}
		tsHolder.sort();
		
		System.out.println("Tarantula ended learning.");
	}

	//実行されたステートメントの集合を返す
	private Set<StatementData> createSDSet(BPDGHolder holder){
		Node node;
		Set<StatementData> sdSet = new HashSet<StatementData>();
		while((node = holder.getNextNode()) != null){
			EventSignature es = BXModelUtility.getEventSignature(node);
			if(es.getSourcePath() == null || es.getLineNumber() == null){
				continue;
			}
			StatementData sd = new StatementData(es);
			sdSet.add(sd);
		}
		return sdSet;
	}

	public void printRanking(IOut out,int num) {
		List<TStatement> tsList = tsHolder.getList();
		int tmp = (num > tsList.size()) ? tsList.size() : num;
		for(int i=0; i < tmp; i++){
			out.println("\t" + i + ":\t" + tsList.get(i).toString());
		}
	}

	public void printAllRanking(IOut out) {
		printRanking(out,tsHolder.getList().size());
	}

	public int calcScore(StatementData fault) {
		return tsHolder.calcScore(fault);
	}
	public int calcScore(List<StatementData> faults) {
		return tsHolder.calcScore(faults);
	}

}
