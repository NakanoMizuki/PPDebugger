package jp.ac.titech.cs.sa.tklab.faultlocalize.tarantula;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementDataFactory;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Thread;

import org.junit.Test;

/**
 * 
 * @author nakano
 *
 */
public class TStatementTest {

	@Test
	public void test() {
		Thread th = new Thread();
		th.setThreadId("1");
		th.setThreadName("name");
		
		StatementDataFactory factory = StatementDataFactory.getInstance();
		
		StatementData sd1 = factory.genStatementData("test","1",th);
		StatementData sd2 = factory.genStatementData("test","2",th);
		StatementData sd3 = factory.genStatementData("test","1",th);
		StatementData sd4 = factory.genStatementData("hoge","1",th);

		TStatement tst1 = new TStatement(sd1);
		TStatement tst2 = new TStatement(sd2);
		TStatement tst3 = new TStatement(sd3);
		TStatement tst4 = new TStatement(sd4);

		assertFalse(tst1.equals(tst2));
		assertTrue(tst1.equals(tst3));
		assertFalse(tst1.equals(tst4));

		Set<TStatement> set = new HashSet<TStatement>();
		set.add(tst1);
		assertTrue(set.contains(tst1));
		assertTrue(set.contains(tst3));
		assertTrue(set.add(tst2));
		assertFalse(set.add(tst3));
		assertTrue(set.add(tst4));
		
	}

}
