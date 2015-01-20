package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FastIntern {
	private static final int NUM_DATA = 65535;
	private static final Map<String,String> pool = new ConcurrentHashMap<String,String>(NUM_DATA);
		
	private FastIntern(){}

	public static String get(String name){
		String ret = pool.get(name);
		if(ret == null){
			synchronized (pool) {
				if( (ret = pool.get(name)) == null ) pool.put(name,name);
			}
			ret = name;
		}
		return ret;
	}
}
