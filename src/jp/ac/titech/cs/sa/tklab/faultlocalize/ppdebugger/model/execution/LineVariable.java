package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution;

import java.util.HashSet;
import java.util.Set;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;

/**
 * 現在実行している行で読み込んだ変数を保持するクラス
 * 実行行が変わると今まで保持していたクラスを捨てる
 * @author nakano
 *
 */
public class LineVariable {
	private StatementData sd;
	private Set<Variable> variables;
	
	
	public LineVariable(){
		sd = null;
		variables = new HashSet<Variable>();
	}
	
	public StatementData getStatementData(){
		return sd;
	}
	public Set<Variable> getVariables(){
		return variables;
	}

	public void reset(){
		sd = null;
		variables.clear();
	}

	public void add(StatementData newLine,Variable var){
		if(newLine.equals(sd)){
			variables.add(var);
		}else{
			sd = newLine;
			variables.clear();
			variables.add(var);
		}
	}
	
	
}
