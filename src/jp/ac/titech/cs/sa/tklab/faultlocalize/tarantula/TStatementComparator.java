package jp.ac.titech.cs.sa.tklab.faultlocalize.tarantula;

import java.util.Comparator;

/**
 * Tarantulaでのランキング時の比較を行うクラス
 * suspiciousが高い方が上位。同じ場合は行数が遅い方が上位
 * @author nakano
 *
 */
class TStatementComparator implements Comparator<TStatement> {

	@Override
	public int compare(TStatement o1, TStatement o2) {
		if(o1.getSuspicious() < o2.getSuspicious()){
			return 1;
		}else if((o1.getSuspicious() == o2.getSuspicious())){
			if(o1.getLineNumber() > o2.getLineNumber()){
				return 1;
			}else{
				return -1;
			}
		}else{
			return -1;
		}
	}
}
