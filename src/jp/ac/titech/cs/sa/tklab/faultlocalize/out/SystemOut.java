package jp.ac.titech.cs.sa.tklab.faultlocalize.out;

/**
 * 標準出力に出力する
 * @author nakano
 *
 */
public class SystemOut implements IOut {

	@Override
	public void println(String str) {
		System.out.println(str);
	}
	@Override
	public void print(String str) {
		System.out.print(str);
	}

}
