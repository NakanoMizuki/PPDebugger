package jp.ac.titech.cs.sa.tklab.faultlocalize.junit;

import static org.junit.Assert.*;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Thread;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;

import org.junit.Test;

/**
 * 
 * @author nakano
 *
 */
public class StatementDataTest {

	@Test
	public void test() {
		Thread th1 = new Thread();
		th1.setThreadId("1");
		th1.setThreadName("name");
		Thread th2 = new Thread();
		th2.setThreadId("2");
		th2.setThreadName("name");
		Thread th3 = new Thread();
		th3.setThreadId("1");
		th3.setThreadName("dummy");
		
		StatementData sd1 = new StatementData("test","1",th1);
		StatementData sd2 = new StatementData("test","2",th1);
		StatementData sd3 = new StatementData("test","1",th1);
		StatementData sd4 = new StatementData("hoge","1",th1);
		StatementData sd5 = new StatementData("test","1",th2);
		StatementData sd6 = new StatementData("test","1",th3);
		
		assertFalse(sd1.equals(sd2));
		assertTrue(sd1.equals(sd3));
		assertFalse(sd1.equals(sd4));
		assertFalse(sd1.equals(sd5));
		assertFalse(sd1.equals(sd6));
	}

}
