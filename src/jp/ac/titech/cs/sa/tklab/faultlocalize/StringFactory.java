package jp.ac.titech.cs.sa.tklab.faultlocalize;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;


public class StringFactory {
	private static StringFactory instance = null;
	private SortedSet<String> set;
	
	private StringFactory(){
		set = new TreeSet<String>(new Comparator<String>() {
			@Override
			public int compare(String s1,String s2){
				return s1.compareTo(s2);
			}
		});
	}
	
	public static StringFactory getInstance(){
		if(instance == null){
			instance = new StringFactory();
		}
		return instance;
	}
	
	public String genString(String newString){
		int hashCode = newString.hashCode();
		synchronized (set) {
			try{
				SortedSet<String> subSet = set.tailSet(newString);	//hashCodeが等しい要素を取り出す
				for(String current: subSet){
					if(current.hashCode() == hashCode && current.equals(newString)){
						return current;					//既にセット内にある場合はセットの中身を返す
					}
				}
			}catch(IllegalArgumentException e){}	//同じhashCodeのものがないとき
			set.add(newString);
		}
		return newString;
	}

}
