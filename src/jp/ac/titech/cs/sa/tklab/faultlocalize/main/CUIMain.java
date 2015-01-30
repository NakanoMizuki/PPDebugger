package jp.ac.titech.cs.sa.tklab.faultlocalize.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.xml.bind.JAXBException;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.Valuation;
import jp.ac.titech.cs.sa.tklab.faultlocalize.out.OutToFile;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.PPDebugger;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result.Result;

/**
 * 
 * @author nakano
 *
 */
public class CUIMain {
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
			dir.mkdirs();
		}
	}


	/**
	 * @param args
	 * @throws JAXBException 
	 */
	public static void main(String[] args) throws JAXBException {
		CUIMain main = null;
		if(args.length == 2){
			main = new CUIMain(args[0], Integer.valueOf(args[1]));
		}else{
			System.out.println("Illeagal arguments.");
			System.out.println("arg[0] = projectPath");
			System.out.println("arg[1] = hopNum");
			return;
		}
		
		long start = System.currentTimeMillis();
		//main.execute(1);
		main.executeAllVersion();
		
		long end = System.currentTimeMillis();
		System.out.println("Time:" + (end - start)+ " (ms)." );
	}


	private void executeAllVersion() throws JAXBException{
		int verNum = new File(tracePath).listFiles().length;
		File resultScore = new File(resultPath + "/score.txt");
		try {
			resultScore.createNewFile();
			@SuppressWarnings("resource")
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(resultScore)));
			for(int i=1; i <= verNum; i++){
				Valuation valuation = execute(i);
				if(valuation == null){
					writer.println("v" + i + " : This version doesn't have failed traces or faults.");
				}else{
					writer.println("v" + i + ": " + valuation.toString());
				}
			}
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Valuation execute(int ver) throws JAXBException{
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
		
		ppdebugger.learn(passedFiles,failedFiles);
		passedFiles = null;
		failedFiles = null;
		ppdebugger.printAllRanking(out);
		Result result = ppdebugger.createResult();
		out.println(result.toString());
		Valuation valuation = result.getValuation(faults);
		if(valuation != null){
			out.println(valuation.toString());
		}
		out.flush();
		return valuation;
	}
	
}
