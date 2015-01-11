package jp.ac.titech.cs.sa.tklab.faultlocalize.junit;

import static org.junit.Assert.*;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementDataFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Thread;

import org.junit.Test;

/**
 * 
 * @author nakano
 *
 */
public class DataDependencyTest {

	@Test
	public void test() {
		Thread th = new Thread();
		th.setThreadId("1");
		th.setThreadName("name");
		StatementDataFactory factory = StatementDataFactory.getInstance();
		
		StatementData sd1 = factory.genStatementData("sample","1",th);
		StatementData sd2 = factory.genStatementData("sample","2",th);
		DataDependency dd1 = new DataDependency("x",sd1);
		DataDependency dd2 = new DataDependency("x",sd1);
		DataDependency dd3 = new DataDependency("y",sd2);
		
		assertTrue(dd1.equals(dd2));
		assertFalse(dd1.equals(dd3));
		
		
		DataDependencySet dds1 = new DataDependencySet(sd1,dd1,1);
		DataDependencySet dds2 = new DataDependencySet(sd1,dd2,1);
		DataDependencySet dds3 = new DataDependencySet(sd1,dd3,1);
		assertTrue(dds1.isLabeled());
		assertFalse(dds3.isLabeled());
		assertTrue(dds1.equals(dds2));
		assertFalse(dds1.equals(dds3));
		
		dds1.addDataDependency(dd3);
		assertNotEquals(dds1,dds2);				//イコールではないが
		assertTrue(dds1.isSame(dds2));		//名前は同じ
		dds2.addDataDependency(dd3);
		assertEquals(dds1,dds2);
		assertTrue(dds1.isSame(dds2));
	
		
	}

}
