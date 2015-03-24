package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution;

import java.util.ArrayList;
import java.util.List;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;

/**
 * 一回の実行結果を保存するクラス
 * @author nakano
 *
 */
public class ExecutionModel {
	private List<Statement> statements;
	private VariableSet vs;
	
	
	public ExecutionModel(){
		statements = new ArrayList<Statement>();
		vs = new VariableSet();
	}
	
	public List<Statement> getStatements(){
		return statements;
	}
	
	public Statement getStatement(StatementData sd){
		for(Statement st :statements){
			if(st.getStatementData().equals(sd)){
				return st;
			}
		}
		return null;
	}
	
	public void addVariable(Variable v){
		vs.updateVariable(v);
	}
	
	public Variable getVariable(String varName){
		return vs.getVariable(varName);
	}
	
	/**
	 * 未登録のステートメントを登録
	 * @param sd
	 */
	public void addStatementData(StatementData sd){
		Statement st = getStatement(sd);
		if(st == null){
			statements.add(new Statement(sd));
		}
	}
	
	/**
	 * 指定されたステートメントにDataDependencySetを追加
	 * まだ登録されていなければ登録
	 * @param sd
	 * @param dds
	 */
	public void addDataDependencySet(StatementData sd,DataDependencySet dds){
		Statement st = getStatement(sd);
		if(st == null){
			st = new Statement(sd);
			st.addDataDependencySet(dds);
			statements.add(st);
		}else{
			st.addDataDependencySet(dds);
		}
	}

}
