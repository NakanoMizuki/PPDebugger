package jp.ac.titech.cs.sa.tklab.faultlocalize.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.xml.bind.JAXBException;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.out.IOut;
import jp.ac.titech.cs.sa.tklab.faultlocalize.out.OutToFile;
import jp.ac.titech.cs.sa.tklab.faultlocalize.tarantula.Tarantula;

public class TarantulaMain {
	private final String DIRNAME = "tarantula-result";
	private final Tarantula tarantula;
	private final String projectPath;
	
	public TarantulaMain(String path){
		tarantula = new Tarantula();
		projectPath = (path+"/").replaceAll("//$", "/");
		File dir = new File(projectPath + DIRNAME);
		if(dir.exists()){
			for(File file : dir.listFiles()){
				file.delete();
			}
		}else{
			dir.mkdir();
		}
	}

	public static void main(String[] args) {
		TarantulaMain launch = new TarantulaMain(args[0]);
		launch.executeAllVersion();
	}
	
	
	
	private void executeAllVersion(){
		File scoreFile = new File(projectPath + DIRNAME + "/score.txt");
		try {
			@SuppressWarnings("resource")
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(scoreFile)));
			int verNum = new File(projectPath + "trace").listFiles().length;
			for(int i=1; i <= verNum; i++){
				int score = execute(i);
				pw.println("ver" + i + " score=" + score);
			}
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int execute(int ver){
		IOut out = new OutToFile(projectPath + DIRNAME + "ver" + ver + "result");
		File[] passedFiles = new File(projectPath + "trace/v" + ver + " pass").listFiles();
		File[] failedFiles = new File(projectPath + "trace/v" + ver + "fail").listFiles();
		List<StatementData> faults = ReadFaults.genFaults(projectPath + "faults/v" + ver + ".txt");
		try {
			tarantula.learn(passedFiles, failedFiles);
			tarantula.printAllRanking(out);
			return tarantula.calcScore(faults);
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

}
