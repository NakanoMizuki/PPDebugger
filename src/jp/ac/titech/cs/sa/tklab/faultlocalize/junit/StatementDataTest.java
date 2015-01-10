package jp.ac.titech.cs.sa.tklab.faultlocalize.junit;

import static org.junit.Assert.*;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Thread;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementDataFactory;

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
		
		StatementDataFactory factory = StatementDataFactory.getInstance();
		
		StatementData sd1 = factory.genStatementData("test","1",th1);
		StatementData sd2 = factory.genStatementData("test","2",th1);
		StatementData sd3 = factory.genStatementData("test","1",th1);
		StatementData sd4 = factory.genStatementData("hoge","1",th1);
		StatementData sd5 = factory.genStatementData("test","1",th2);
		StatementData sd6 = factory.genStatementData("test","1",th3);
		
		assertFalse(sd1.equals(sd2));
		assertTrue(sd1.equals(sd3));
		assertTrue(sd1 == sd3);				//同一インスタンスのはず
		assertFalse(sd1.equals(sd4));
		assertFalse(sd1.equals(sd5));
		assertFalse(sd1.equals(sd6));		//IDが同じスレッドなら名前は違くても等しい
		assertTrue(sd1 != sd6);				//スレッドが異なるので同一インスタンスではない
		
	}

}
