package jp.ac.titech.cs.sa.tklab.faultlocalize.junit;

import static org.junit.Assert.*;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.NameCreator;

import org.junit.Test;

public class ReplaceTest {

	@Test
	public void test() {
		String name = "test#static";
		assertEquals("test", NameCreator.removeSuffix(name));
		
		name = "test#fea#fae";
		assertEquals("test", NameCreator.removeSuffix(name));
	}

}
