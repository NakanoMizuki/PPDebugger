package jp.ac.titech.cs.sa.tklab.faultlocalize.tarantula;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementDataFactory;

public class TraceReader {
	private static final StatementDataFactory factory = StatementDataFactory.getInstance();
	
	
	public static Set<StatementData> createSDSet(File file) throws IOException{
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		Set<StatementData> sdSet = new HashSet<StatementData>();
		String line;
		while((line=reader.readLine()) != null){
			String[] tokens = line.split(",");
			StatementData sd = factory.genStatementData(tokens[0], tokens[1]);
			sdSet.add(sd);
		}
		return sdSet;
	}
}
