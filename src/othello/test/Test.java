package othello.test;


import java.util.*;

import othello.common.*;

//単なるテスト用
public class Test {

	public static void main(String args[]){
/*
		ArrayList<String> str = new ArrayList<String>();

		str.add("abc");
		str.add("cde");
		str.add("111");
		
		System.out.println(str.size() + "\n");
		
		str.add(1, "あああ");
		
		System.out.println(str.get(0));
		System.out.println(str.get(1));
		System.out.println(str.get(2));
		System.out.println(str.get(3));
		
*/
		Counter cnt = new Counter();
		
		System.out.println("Start!");
		
		for(cnt.setValue(0); cnt.getValue() < 10; cnt.countUp()){
			System.out.println(cnt.getValue());
		}
		
		System.out.println("Count End! and Reset");
		cnt.reset();
		System.out.println(cnt.getValue());
	}

}
