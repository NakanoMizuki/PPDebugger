package jp.ac.titech.cs.sa.tklab.faultlocalize.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	private static final int HOPNUM = 0;
	private final PPDebugger ppdebugger;
	private final String tracePath;
	private final String faultPath;
	private final String resultPath;
	
	private CUIMain(String path,int hopNum){
		ppdebugger = new PPDebugger(hopNum);
		String projectPath = (path+"/").replaceAll("//$", "/");
		tracePath = projectPath + "trace";
		faultPath = projectPath + "faults";
		resultPath = projectPath + "result/hop" + hopNum;
		File dir = new File(resultPath);
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
		CUIMain main = null;
		if(args.length == 1){
			main = new CUIMain(args[0],HOPNUM);
		}else if(args.length == 2){
			main = new CUIMain(args[0], Integer.valueOf(args[1]));
		}else{
			System.out.println("Illeagal arguments");
			System.out.println("arg[0] = projectPath");
			System.out.println("(arg[1] = hopNum)");
			return;
		}
		
		long start = System.currentTimeMillis();
		main.execute(1);
		//main.executeAllVersion();
		
		long end = System.currentTimeMillis();
		System.out.println("Time:" + (end - start)+ " (ms)." );
	}


	private void executeAllVersion() throws JAXBException{
		int verNum = new File(tracePath).listFiles().length;
		File resultScore = new File(resultPath + "/score.txt");
		try {
			@SuppressWarnings("resource")
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(resultScore)));
			for(int i=1; i <= verNum; i++){
				Score score = execute(i);
				if(score == null){
					writer.println("v" + i + " : This version doesn't have failed traces or faults.");
				}else{
					writer.println("v" + i + " : " + score.toString());
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
		File[] passedFiles = new File(tracePath + "/v" + ver + "/pass").listFiles();
		File[] failedFiles = new File(tracePath + "/v" + ver + "/fail").listFiles();
		OutToFile out = new OutToFile(resultPath +  "/ver" + ver + "-result.txt");
		if(failedFiles.length == 0){
			System.out.println("This version doesn't have failed traces.");
			out.println("This version doesn't have failed traces.");
			return null;
		}
		List<StatementData> faults = ReadFaults.genFaults(faultPath + "/v" + ver + ".txt");
		if(faults == null || faults.isEmpty()){
			System.out.println("This version doesn't have faults.");
			out.println("This version doesn't have faults.");
			return null;
		}
		
		ppdebugger.learn(passedFiles);
		passedFiles = null;
		ppdebugger.printAllRanking(out);

		
		out.println(failedFiles.length + "failedFiles----------" );
		int max=0,min=Integer.MAX_VALUE,sum=0;
		boolean flag=false;
		for(Result result: ppdebugger.createResults(failedFiles)){
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
	
}
