package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger;

import java.io.File;
import java.util.concurrent.Callable;

import javax.xml.bind.JAXBException;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementDataFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.Creator;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.pass.PassedModel;

public class Executor implements Callable<Void>{
	private final PassedModel passedModel;
	private File file;
	private final StatementDataFactory factory;
	private final int hopNum;
	
	
	public Executor(PassedModel passedModel,File file,StatementDataFactory factory,int hopNum){
		this.passedModel = passedModel;
		this.file = file;
		this.factory = factory;
		this.hopNum = hopNum;
	}

	@Override
	public Void call() throws Exception {
		try {
			ExecutionModel em = new Creator(factory).createExecutionModel(file, hopNum);
			file = null;
			passedModel.merge(em);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
