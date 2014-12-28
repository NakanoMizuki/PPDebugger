package jp.ac.titech.cs.sa.tklab.faultlocalize.junit;

import static org.junit.Assert.*;

import java.util.List;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Statement;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Thread;

import org.junit.Test;

public class StatementTest {

	@Test
	public void test() {
		Thread th = new Thread();
		th.setThreadId("1");
		th.setThreadName("test");
		StatementData sd = new StatementData("test","1",th);
		DataDependency dd = new DataDependency("x",sd);
		DataDependencySet dds1 = new DataDependencySet(sd,dd);
		DataDependencySet dds2 = new DataDependencySet(sd,dd);
		
		Statement st = new Statement(sd);
		
		st.addDataDependencySet(dds1);
		st.addDataDependencySet(dds2);		//同様のDataDependencySetを追加する
		
		List<DataDependencySet> list = st.getDataDependencySets();
		assertEquals(1,list.size());		//DataDependencySetは重複しないので一つ
		DataDependencySet ddsTmp = list.get(0);
		assertEquals(dds1,ddsTmp);
	}

}
