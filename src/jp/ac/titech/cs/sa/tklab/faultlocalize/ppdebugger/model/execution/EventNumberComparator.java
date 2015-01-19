package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution;

import java.util.Comparator;

import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;

public class EventNumberComparator implements Comparator<DataDependencySet>{
	@Override
	public int compare(DataDependencySet dds1,DataDependencySet dds2){		//eventNumberの降順
		if(dds1.getEventNumber() == dds2.getEventNumber()) return 0;
		if(dds1.getEventNumber() < dds2.getEventNumber()) return 1;
		return -1;
	}
}
