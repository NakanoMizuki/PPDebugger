package jp.ac.titech.cs.sa.tklab.faultlocalize;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Thread;
import jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil.EventSignature;


public class StatementDataFactory {
	private static StatementDataFactory instance = null;
	private SortedSet<StatementData> sdSet;
	private StringFactory stringFactory;
	
	
	private StatementDataFactory(){
		stringFactory = StringFactory.getInstance();
		sdSet = new TreeSet<StatementData>(new Comparator<StatementData>() {
			@Override
			public int compare(StatementData sd1,StatementData sd2){	//hashCodeの昇順
				if(sd1.hashCode() == sd2.hashCode()) return 0;
				if(sd1.hashCode() < sd2.hashCode()) return -1;
				return 1;
			}
		});
	}
	
	public static StatementDataFactory getInstance(){
		if(instance == null){
			instance = new StatementDataFactory();
		}
		return instance;
	}
	
	public StatementData genStatementData(String sourcePath,int lineNumber,Thread thread){
		StatementData newSD = new StatementData(stringFactory.genString(sourcePath),stringFactory.genString(Integer.toString(lineNumber)),thread);
		//hashCodeが1だけ異なるインスタンスを生成。これは比較のためだけに用いる
		StatementData dummy = new StatementData(sourcePath, Integer.toString(lineNumber+1),thread);
		synchronized (sdSet) {
			try{
				SortedSet<StatementData> sameHashSet = sdSet.subSet(newSD,dummy);	//hashCodeが等しい要素を取り出す
				for(StatementData tmp: sameHashSet){
					if(tmp.equals(newSD)){
						return tmp;					//既にセット内にある場合はセットの中身を返す
					}
				}
			}catch(IllegalArgumentException e){}	//同じhashCodeのものがないとき
			sdSet.add(newSD);
		}
		return newSD;
	}
	public StatementData genStatementData(String sourcePath,String lineNumber){
		return genStatementData(sourcePath,lineNumber,null);
	}
	public StatementData genStatementData(String sourcePath,String lineNumber,Thread thread){
		return genStatementData(sourcePath, Integer.valueOf(lineNumber), thread);
	}
	public StatementData genStatementData(EventSignature es){
		return genStatementData(es.getSourcePath(),es.getLineNumber(),es.getThread());
	}
}
