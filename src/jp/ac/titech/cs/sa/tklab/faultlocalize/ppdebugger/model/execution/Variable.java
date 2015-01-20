package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableDefinition;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.FastIntern;

public class Variable {
	private final String varName;
	private VariableDefinition latestdef;

	public Variable(String varName,VariableDefinition def) {
		this.varName = FastIntern.get(varName);
		latestdef = def;
	}
	
	public String getVarName(){
		return varName;
	}
	public VariableDefinition getLatestDefinition(){
		return latestdef;
	}
	public void updateDefinition(VariableDefinition def){
		latestdef = def;
	}
	
	public boolean isSameVariable(Variable variable){
		if(varName.equals(variable.getVarName())){
			return true;
		}
		return false;
	}
	
	public boolean isSameVariable(String name){
		if(varName.equals(name)) return true;
		return false;
	}

}
