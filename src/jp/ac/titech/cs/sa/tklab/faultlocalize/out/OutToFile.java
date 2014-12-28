package jp.ac.titech.cs.sa.tklab.faultlocalize.out;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 
 * @author nakano
 *
 */
public class OutToFile implements IOut {
	private final String fileName;
	private PrintWriter pw = null;
	
	public OutToFile(String path){
		fileName = path;
		File file = new File(path);
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			pw = new PrintWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getFileName(){
		return fileName;
	}
	
	@Override
	public void println(String str) {
		pw.println(str);
	}
	
	@Override
	public void print(String str) {
		pw.print(str);
	}
	
	public void flush(){
		pw.flush();
	}


}
