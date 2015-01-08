package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;

import java.util.ArrayList;
import java.util.List;

public class Scope {
	private static final String DELIMITER="-";
	private List<Integer> list;
	private int next;
	
	public Scope(){
		list = new ArrayList<Integer>();
		clear();
	}
	
	public void clear(){
		list.clear();
		next = 0;
	}
	
	public void entry(){
		list.add(next);
	}

	public void exit(){
		next = list.get(list.size()-1) +1;
		list.remove(list.size() -1);
	}
	
	
	@Override
	public String toString(){
		String str="";
		for(int value : list){
			str += DELIMITER + value;
		}
		return str;
	}
}
