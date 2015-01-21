package jp.ac.titech.cs.sa.tklab.faultlocalize.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.out.OutToFile;
import jp.ac.titech.cs.sa.tklab.faultlocalize.tarantula.Tarantula;

public class TarantulaMain {
	private final String DIRNAME = "tarantula-result";
	private final String TRACE_DIRNAME = "tarantula-trace";
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
			dir.mkdirs();
		}
	}

	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("Illegal Arguments");
			System.out.println("arg[0] = projectPath");
			return;
		}
		TarantulaMain launch = new TarantulaMain(args[0]);
		
		long start = System.currentTimeMillis();
		launch.executeAllVersion();
		long end = System.currentTimeMillis();
		System.out.println("Time:" + (end - start)+ " (ms)." );
	}
	
	
	
	private void executeAllVersion(){
		File scoreFile = new File(projectPath + DIRNAME + "/score.txt");
		try {
			@SuppressWarnings("resource")
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(scoreFile)));
			int verNum = new File(projectPath + TRACE_DIRNAME).listFiles().length;
			for(int i=1; i <= verNum; i++){
				int score = execute(i);
				if(score == 0){
					pw.println("v" + i + ": doesn't have fault.");
				}else if(score == -1){
					pw.println("v" + i + ": error!");
				}else{
					pw.println("v" + i + ": score=" + score);
				}
			}
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private int execute(int ver){
		System.out.println("Ver" + ver + "start");
		OutToFile out = new OutToFile(projectPath + DIRNAME + "/ver" + ver + "-result.txt");
		File[] passedFiles = new File(projectPath + TRACE_DIRNAME + "/v" + ver + "/pass").listFiles();
		File[] failedFiles = new File(projectPath + TRACE_DIRNAME + "/v" + ver + "/fail").listFiles();
		if(failedFiles.length == 0){
			return 0;
		}
		List<StatementData> faults = ReadFaults.genFaults(projectPath + "faults/v" + ver + ".txt");
		if(faults == null || faults.isEmpty()){
			return 0;
		}
		
		try {
			tarantula.learn(passedFiles, failedFiles);
			tarantula.printAllRanking(out);
			out.flush();
			return tarantula.calcScore(faults);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
		return -1;
	}

}
