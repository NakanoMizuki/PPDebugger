package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;

public class Statement {
	private final StatementData sd;
	/**
	 * 一段階のデータ依存で作られたものを保存する。
	 * イベント番号順に並べて保存
	 */
	private SortedSet<DataDependencySet> originals;
	
	/**
	 * すべてのデータ依存を保存。伝播後の結果も持つ
	 */
	private List<DataDependencySet> ddsList;
	
	/**
	 * まだ依存伝播が行われていないものを保存
	 * このセットに入っているものからさかのぼった結果がnextPropagationに追加される
	 */
	private Set<DataDependencySet> propagatables;
	
	/**
	 * 伝播された依存情報を持つ
	 * ここに保存されたものがddlistに追加される
	 */
	private Set<DataDependencySet> nextPropagations;
	
	
	
	public Statement(StatementData sd){
		this.sd = sd;
		originals = new TreeSet<DataDependencySet>(new Comparator<DataDependencySet>() {
			@Override
			public int compare(DataDependencySet dds1,DataDependencySet dds2){
				if(dds1.getEventNumber() == dds2.getEventNumber()) return 0;
				if(dds1.getEventNumber() < dds2.getEventNumber()) return -1;
				return 1;
			}
		});
		ddsList = new ArrayList<DataDependencySet>();
		propagatables = new HashSet<DataDependencySet>();
		nextPropagations = new HashSet<DataDependencySet>();
	}
	
	public List<DataDependencySet> getDataDependencySets(){
		return ddsList;
	}
	
	public void setDataDependencySets(List<DataDependencySet> lists){
		ddsList = lists;
	}
	public StatementData getStatementData(){
		return sd;
	}
	public SortedSet<DataDependencySet> getOriginals(){
		return originals;
	}
	public Set<DataDependencySet> getPropagatables(){
		return propagatables;
	}
	
	
	/**
	 * 要素の追加、重複する要素があった場合には中身のみ追加する
	 * @param dds　追加したい要素
	 * @return　重複する要素がすでに存在するときにtrue、初めての要素ならfalseを返す
	 */
	private boolean addDuplication(DataDependencySet dds){
		for(DataDependencySet contentdds : ddsList){
			if(contentdds.isSame(dds)){
				for(DataDependency dd :dds.getSet()){
					contentdds.addDataDependency(dd);
				}
				return true;
			}
		}
		
		ddsList.add(dds);
		return false;
	}
	
	/**
	 * このメソッドによって一段階のデータ依存が作られる
	 * @param dds 追加するインスタンス
	 */
	public void addDataDependencySet(DataDependencySet dds){
		boolean duplicate = addDuplication(dds);
		if(!duplicate){
			originals.add(dds);
			propagatables.add(dds);
		}
	}
	
	
	
	public void addNextPropagation(DataDependencySet dds){
		nextPropagations.add(dds);
	}

	/**
	 * 伝播を行うメソッド
	 * 伝播予定に入っていたものを追加し、伝播を行ったものを削除する
	 */
	public void propagate(){
		propagatables.clear();
		for(DataDependencySet dds : nextPropagations){
			if(!ddsList.contains(dds)){
				addDuplication(dds);
				propagatables.add(dds);
			}
		}
		nextPropagations.clear();
	}
	
	@Override
	public String toString(){
		return "sd=" + sd.toString();
	}
}
