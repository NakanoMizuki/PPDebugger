package jp.ac.titech.cs.sa.tklab.faultlocalize.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.out.OutToFile;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.PPDebugger;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result.Result;

/**
 * 
 * @author nakano
 *
 */
public class CUIMain {
	private static final int HOPNUM = 5;
	private final PPDebugger ppDebugger;
	private final String projectPath;
	
	private CUIMain(String path,int hopNum){
		projectPath = (path+"/").replaceAll("//$", "/");
		ppDebugger = new PPDebugger(hopNum);
		File dir = new File(projectPath + "result");
		if(dir.exists()){
			for(File file : dir.listFiles()){
				file.delete();
			}
		}else{
			dir.mkdir();
		}
	}


	/**
	 * @param args
	 * @throws JAXBException 
	 */
	public static void main(String[] args) throws JAXBException {
		if(args.length != 1){
			System.out.println("Illeagal arguments");
			return;
		}
		
		CUIMain main = new CUIMain(args[0],HOPNUM);
		
		long start = System.currentTimeMillis();
		main.execute(7);
		//main.executeAllVersion();
		
		long end = System.currentTimeMillis();
		System.out.println("Time:" + (end - start)+ " (ms)." );
	}


	private void executeAllVersion() throws JAXBException{
		int verNum = new File(projectPath+"trace").listFiles().length;
		File resultScore = new File(projectPath + "result/score.txt");
		try {
			@SuppressWarnings("resource")
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(resultScore)));
			for(int i=1; i <= verNum; i++){
				Score score = execute(i);
				if(score == null){
					writer.println("ver" + i + " : This version doesn't have failed traces.");
				}else{
					writer.println("ver" + i + " : " + score.toString());
				}
			}
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Score execute(int ver) throws JAXBException{
		System.out.println("Ver" + ver + " start");
		String dirPath = projectPath + "trace/v" + ver + "/";
		File[] passedFiles = new File(dirPath + "pass").listFiles();
		File[] failedFiles = new File(dirPath + "fail").listFiles();
		OutToFile out = new OutToFile(projectPath + "result/ver" + ver + "-result.txt");
		if(failedFiles.length == 0){
			System.out.println("This version doesn't have failed traces.");
			out.println("This version doesn't have failed traces.");
			return null;
		}
		
		ppDebugger.learn(passedFiles);
		ppDebugger.printAllRanking(out);

		
		out.println(failedFiles.length + "failedFiles----------" );
		File faultFile = new File(projectPath + "faults/v" + ver + ".txt");
		List<StatementData> faults = readFaults(faultFile);
		int max=0,min=Integer.MAX_VALUE,sum=0;
		boolean flag=false;
		for(File file : failedFiles){
			Result result = ppDebugger.check(file);
			out.println(result.toString());
			int score = result.calcScore(faults);
			if(score != -1){
				max = Math.max(max, score);
				min = Math.min(min, score);
				sum += score;
			}else{
				flag = true;
			}
		}
		Score score;
		if(flag == true){
			score =  new Score(max, min);
		}else{
			double avg = (double)sum / failedFiles.length;
			score= new Score(max, min, avg);
		}
		out.println("Score:" + score.toString());
		out.flush();
		return score;
	}
	
	
	private List<StatementData> readFaults(File faultFile){
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
