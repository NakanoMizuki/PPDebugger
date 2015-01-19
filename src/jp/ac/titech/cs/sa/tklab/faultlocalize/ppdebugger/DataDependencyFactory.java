package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StringFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;

public class DataDependencyFactory {
	private static DataDependencyFactory instance = null;
	private SortedSet<DataDependency> dataDependencies;
	private StringFactory stringFactory;
	
	
	private DataDependencyFactory() {
		stringFactory = StringFactory.getInstance();
		dataDependencies = new TreeSet<DataDependency>(new Comparator<DataDependency>() {
			@Override
			public int compare(DataDependency dd1,DataDependency dd2){	//hashCodeの昇順
				if(dd1.hashCode() == dd2.hashCode()) return 0;
				if(dd1.hashCode() < dd2.hashCode()) return -1;
				return 1;
			}});
	}
	
	public static DataDependencyFactory getInstance(){
		if(instance == null){
			instance = new DataDependencyFactory();
		}
		return instance;
	}
	
	public DataDependency genDataDependency(String varName,StatementData sd){
		DataDependency newdd = new DataDependency(stringFactory.genString(varName), sd);	
		//hashCodeが1だけ異なるインスタンスを生成。これは比較のためだけに用いる
		StatementData dummySd = new StatementData(sd.getSourcePath(),Integer.toString(sd.getLineNumber() +1),sd.getThread());
		DataDependency dummy = new DataDependency(varName, dummySd);
		
		synchronized (dataDependencies) {
			try{
				SortedSet<DataDependency> sameHashSet = dataDependencies.subSet(newdd,dummy);	//hashCodeが等しい要素を取り出す
				for(DataDependency tmp: sameHashSet){
					if(tmp.equals(newdd)){
						return tmp;					//既にセット内にある場合はセットの中身を返す
					}
				}
			}catch(IllegalArgumentException e){}	//同じhashCodeのものがないとき
			dataDependencies.add(newdd);
		}
		return newdd;
	}
}
