package jp.ac.titech.cs.sa.tklab.faultlocalize.main;

import java.io.File;

import javax.swing.JFileChooser;
import javax.xml.bind.JAXBException;

import jp.ac.titech.cs.sa.tklab.faultlocalize.out.OutToFile;
import jp.ac.titech.cs.sa.tklab.faultlocalize.out.SystemOut;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.PPDebugger;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result.Result;
import jp.ac.titech.cs.sa.tklab.faultlocalize.tarantula.Tarantula;

/**
 * 
 * @author nakano
 *
 */
public class GUIMain {

	//ファイル選択時のデフォルトディレクトリ
	private static final String DIRECTORY = "C:/Users/nakano/Desktop";
	//private static final String DIRECTORY = "C:/Users/nakano/Dropbox/research/ohnuma/data/xml";
	

	private static final int HOPNUM = 5;
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GUIMain main = new GUIMain();
		main.start();
	}

	private void start(){
		OutToFile out  = new OutToFile("C:/Users/nakano/Desktop/ppdebugger.txt");
		
		File[] passedFiles = getFiles();
		File[] failedFiles = getFiles();
		//Tarantula tarantula = new Tarantula(new SystemOut());
		//Tarantula tarantula = new Tarantula(new OutToFile("C:/Users/nakano/Desktop/experiment/file.txt"));
		PPDebugger ppdebugger = new PPDebugger(HOPNUM);
		
		
		
		try {
			//tarantula.learn(passedFiles, failedFiles);
			//tarantula.printRanking(RANKNUM);
			//tarantula.printAllRanking();
			ppdebugger.learn(passedFiles);
			ppdebugger.printAllRanking(out);
			for(Result result: ppdebugger.createResults(failedFiles)){
				out.println(result.toString());
			}
			out.flush();
			System.out.println("end.");
			System.out.println("Write to" + out.getFileName());
			
		} catch (JAXBException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	private File[] getFiles(){
		File[] files = null;

		JFileChooser chooser = new JFileChooser(DIRECTORY);
		chooser.setMultiSelectionEnabled(true);
		int chosen = chooser.showOpenDialog(null);
		if (chosen == JFileChooser.APPROVE_OPTION){
			files = chooser.getSelectedFiles();
		}
		return files;
	}
}