package test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		List<String> list = new ArrayList<>();
//		list.add("");
//		list.add("");
//		list.add("");
//		list.add("1");
//		list.add("2");
//		System.out.println(list.size());
		String value = "ÄãºÃ¶Ëµã·¢abc";
		String[] s = value.split("");
		
		System.out.println(s.length);
		for (int i = 0; i < s.length; i++) {
			System.out.println(s[i]);
		}
	}

}
