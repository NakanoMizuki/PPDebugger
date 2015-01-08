package jp.ac.titech.cs.sa.tklab.faultlocalize.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;

public class ReadFaults {
	public static List<StatementData> genFaults(String path){
		File faultFile = new File(path);
		List<StatementData> faults = new ArrayList<StatementData>();
		if(!faultFile.exists()){
			return faults;
		}
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(faultFile));
			String line;
			while((line = reader.readLine()) != null){
				if(line.isEmpty()) break;
				String[] tokens = line.split(",");
				faults.add(new StatementData(tokens[0],tokens[1]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return faults;
	}
}
