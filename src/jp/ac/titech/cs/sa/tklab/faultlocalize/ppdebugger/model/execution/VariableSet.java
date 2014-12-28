package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution;

import java.util.HashSet;
import java.util.Set;

public class VariableSet {
	private Set<Variable> set;

	public VariableSet() {
		set = new HashSet<Variable>();
	}
	
	/**
	 * 処理効率を上げるために一度インスタンスを作成したらそれ以降はinitで初期化だけするようにする
	 * できるだけnewしない
	 */
	public void init(){
		set.clear();
	}
	
	
	public Variable getVariable(String varName) {
		for (Variable v : set) {
			if (v.isSameVariable(varName))
				return v;
		}
		return null;
	}

	public void updateVariable(Variable variable) {
		for (Variable v : set) {
			if (v.isSameVariable(variable)) {
				v.updateDefinition(variable.getLatestDefinition());
				return;
			}
		}
		set.add(variable);
	}

}
