package jp.ac.titech.cs.sa.tklab.faultlocalize.main;

import java.io.File;

import javax.swing.JFileChooser;
import javax.xml.bind.JAXBException;

import jp.ac.titech.cs.sa.tklab.faultlocalize.out.OutToFile;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.PPDebugger;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result.Result;

/**
 * 
 * @author nakano
 *
 */
public class GUIMain {
	
	private static final String DIRECTORY = "C:/Users/nakano/Desktop";	//ファイル選択時のデフォルトディレクトリ
	private static final int HOPNUM = 5;	//伝播数
	

	public static void main(String[] args) {
		GUIMain main = new GUIMain();
		main.start();
	}

	private void start(){
		OutToFile out  = new OutToFile(DIRECTORY + "/ppdebugger.txt");
		File[] passedFiles = getFiles();
		File[] failedFiles = getFiles();
		PPDebugger ppdebugger = new PPDebugger(HOPNUM);
		
		try {
			ppdebugger.learn(passedFiles,failedFiles);
			ppdebugger.printLearnedModel(out);
			Result result = ppdebugger.createResult();
			out.println(result.toString());
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