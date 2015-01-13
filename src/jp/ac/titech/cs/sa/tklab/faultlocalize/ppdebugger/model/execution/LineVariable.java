package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution;

import java.util.ArrayList;
import java.util.List;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;

/**
 * 現在実行している行で読み込んだ変数を保持するクラス
 * 実行行が変わると今まで保持していた変数を捨てる
 * @author nakano
 *
 */
public class LineVariable {
	private StatementData sd;
	private List<Variable> variables;
	
	
	public LineVariable(){
		sd = null;
		variables = new ArrayList<Variable>();
	}
	
	public StatementData getStatementData(){
		return sd;
	}
	public List<Variable> getVariables(){
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
