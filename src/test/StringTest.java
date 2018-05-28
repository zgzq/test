package test;

import java.util.ArrayList;
import java.util.List;

public class StringTest {

	/**
	 * 删除  切割
	 * @param source 原来的
	 * @param div 删除的
	 * @return
	 */
	public static String[] split(String source, String div) {
		int arynum = 0;
		int intIdx = 0;
		int intIdex = 0;
		int div_length = div.length();
		if (source.compareTo("") != 0) {
			if (source.indexOf(div) != -1) {
				intIdx = source.indexOf(div);
				int intCount = 1;
				do {
					if (source.indexOf(div, intIdx + div_length) != -1) {
						intIdx = source.indexOf(div, intIdx + div_length);
						arynum = intCount;
					} else {
						arynum += 2;
						break;
					}
					intCount++;
				} while (true);
			} else {
				arynum = 1;
			}
		} else {
			arynum = 0;
		}
		intIdx = 0;
		intIdex = 0;
		String returnStr[] = new String[arynum];
		if (source.compareTo("") != 0) {
			if (source.indexOf(div) != -1) {
				intIdx = source.indexOf(div);
				returnStr[0] = source.substring(0, intIdx);
				int intCount = 1;
				do {
					if (source.indexOf(div, intIdx + div_length) != -1) {
						intIdex = source.indexOf(div, intIdx + div_length);
						returnStr[intCount] = source.substring(intIdx + div_length, intIdex);
						intIdx = source.indexOf(div, intIdx + div_length);
					} else {
						returnStr[intCount] = source.substring(intIdx + div_length, source.length());
						break;
					}
					intCount++;
				} while (true);
			} else {
				returnStr[0] = source.substring(0, source.length());
				return returnStr;
			}
		} else {
			return returnStr;
		}
		return returnStr;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*String[] strs = split("adbfgadfafdadsssfferfad","ad");
		for(String s:strs){
			System.out.println(s);
		}*/
		/*String signword = "【短信aaaa】";
		String signword1 = "【短信aaaf】";
		String signword2 = "【短信aaab】";
		String signword3 = "【短信aaac】";
		String signword4 = "【短信aaad】";
		//List<String> list= getExtList(signword);
		//List<String> list1= getExtList(signword1);
		List<String> list= getExtList2(signword);
		List<String> list1= getExtList2(signword1);
		List<String> list2= getExtList2(signword2);
		List<String> list3= getExtList2(signword3);
		List<String> list4= getExtList2(signword4);
		//System.out.println(list.size());
		for(String s:list){
			System.out.println(s);
		}
		System.out.println("--------------");
		for(String s:list1){
			System.out.println(s);
		}
		System.out.println("--------------");
		for(String s:list2){
			System.out.println(s);
		}
		System.out.println("--------------");
		for(String s:list3){
			System.out.println(s);
		}
		System.out.println("--------------");
		for(String s:list4){
			System.out.println(s);
		}*/
		String ext_true = "";
		String signword = "【短信aaaa】";
		String signword1 = "【短信aaaf】";
		String signword2 = "【短信aaab】";
		List<String> lists= new ArrayList<>();
		List<String> list= getExtList(signword);
		List<String> list1= getExtList(signword1);
		List<String> list2= getExtList(signword2);
		
		System.out.println("--------------");
		if (lists == null) {
			if (list.size()>0) {
				ext_true = list.get(0);
			}
		}else{
			for (int i = 0; i < list.size(); i++) {
				if (!lists.contains(list.get(i))) {
					ext_true = list.get(i);
					break;
				}
			}
		}
		System.out.println("--------------");
		System.out.println("--------------");
		System.out.println("--------------");
		System.out.println(ext_true);
		/*for(String s:list){
			lists.add(s);
			//System.out.println(s);
		}*/
		lists.add(ext_true);
		
		System.out.println("--------------");
		if (lists == null) {
			if (list1.size()>0) {
				ext_true = list1.get(0);
			}
		}else{
			for (int i = 0; i < list1.size(); i++) {
				if (!lists.contains(list1.get(i))) {
					ext_true = list1.get(i);
					break;
				}
			}
		}
		System.out.println("--------------");
		System.out.println("--------------");
		System.out.println("--------------");
		System.out.println(ext_true);
		System.out.println("--------------");
		/*for(String s:list1){
			lists.add(s);
			//System.out.println(s);
		}*/
		lists.add(ext_true);
		
		
		System.out.println("--------------");
		if (lists == null) {
			if (list2.size()>0) {
				ext_true = list2.get(0);
			}
		}else{
			for (int i = 0; i < list2.size(); i++) {
				if (!lists.contains(list2.get(i))) {
					ext_true = list2.get(i);
					break;
				}
			}
		}
		System.out.println("--------------");
		System.out.println("--------------");
		System.out.println("--------------");
		System.out.println(ext_true);
		
		
		
		
		
	
		
	}
	
	public static List<String> getExtList(String signword){
		String top = "3";
		String signwordContent = signword.replace("【", "");
		signwordContent = signwordContent.replace("】", "");
		
		List<String> list = new ArrayList<String>();
		char[]chars = signwordContent.toCharArray(); //把字符中转换为字符数组
//		 for(int i=0;i<chars.length;i++){//输出结果
//            System.out.println(" "+chars[i]+" "+(int)chars[i]);
//         }
		
		String signwordASCII = "";
		 for(int i=0;i<chars.length;i++){//输出结果
			 signwordASCII+=(int)chars[i];
	     }
		 int ext_num = 5;//扩展码位数
//		 int len = signwordASCII.length()/ext_num;
		 //第一种
		 for(int i = 0;i<signwordASCII.length()/ext_num;i++){
			 String ext = signwordASCII.substring(i*ext_num,(i+1)*ext_num);
			 
//			 System.out.println("扩展码："+ext);
			 list.add(top+ext);
		 }
		 
		//第二种
		 String ext = "";
		 for (int r = 0; r < signwordASCII.length(); r++) {
			 ext = signwordASCII.substring(r);
			 if (ext.length() < ext_num) {
				 for (int t = 0,lent = (ext_num-ext.length())/signwordASCII.length(); t < lent; t++) {
					 ext += signwordASCII;
				 }
//				 System.out.println("ext:"+ext+"  "+(ext_num%ext.length()));
				 ext += signwordASCII.substring(0,ext_num-ext.length());
				 
			 }else if(ext.length() > ext_num){
				 ext = ext.substring(0,ext_num);
			 }
			 
//			 System.out.println("第二种扩展码："+ext);
			 list.add(top+ext);
		 }
		 return list;
	}
	
	/**
	 * 扩展码1 1-5 6-10...
	 * @param signword
	 * @return
	 */
	public static List<String> getExtList3(String signword){
		String top = "3";
		String signwordContent = signword.replace("【", "");
		signwordContent = signwordContent.replace("】", "");
		//System.out.println(signwordContent.length());
		List<String> list = new ArrayList<String>();
		char[]chars = signwordContent.toCharArray(); //把字符中转换为字符数组
//		 for(int i=0;i<chars.length;i++){//输出结果
//            System.out.println(" "+chars[i]+" "+(int)chars[i]);
//         }
		//System.out.println(chars.length);
		String signwordASCII = "";
		 for(int i=0;i<chars.length;i++){//输出结果
			 int chs = (int)chars[i];
			 System.out.println(chs);
			 if(chs>10000){
				 signwordASCII+=(int)chars[i];
			 }else if(chs>0 && chs<100){
				 signwordASCII+="000";
				 signwordASCII+=(int)chars[i];
			 }else if(chs>100 && chs <1000){
				 signwordASCII+="00";
				 signwordASCII+=(int)chars[i];
			 }
			 //System.out.println(signwordASCII);
	     }
		 int ext_num = 5;//扩展码位数
//		 int len = signwordASCII.length()/ext_num;
		 //第一种
		 for(int i = 0;i<signwordASCII.length()/ext_num;i++){
			 String ext = signwordASCII.substring(i*ext_num,(i+1)*ext_num);
			 
			 System.out.println("扩展码："+ext);
			 list.add(top+ext);
		 }
		 return list;
	}
	/**
	 * 扩展码2 1-5 2-6 3-7....
	 * @param signword
	 * @return
	 */
	public static List<String> getExtList2(String signword){
		String top = "3";
		String signwordContent = signword.replace("【", "");
		signwordContent = signwordContent.replace("】", "");
		
		List<String> list = new ArrayList<String>();
		char[]chars = signwordContent.toCharArray(); //把字符中转换为字符数组
//		 for(int i=0;i<chars.length;i++){//输出结果
//            System.out.println(" "+chars[i]+" "+(int)chars[i]);
//         }
		
		String signwordASCII = "";
		 for(int i=0;i<chars.length;i++){//输出结果
			 signwordASCII+=(int)chars[i];
			/* int chs = (int)chars[i];
			 System.out.println(chs);
			 if(chs>10000){
				 signwordASCII+=(int)chars[i];
			 }else if(chs>0 && chs<100){
				 signwordASCII+="000";
				 signwordASCII+=(int)chars[i];
			 }else if(chs>100 && chs <1000){
				 signwordASCII+="00";
				 signwordASCII+=(int)chars[i];
			 }*/
	     }
		 int ext_num = 5;//扩展码位数
		 String ext = "";
		 for (int r = 0; r < signwordASCII.length(); r++) {
			 ext = signwordASCII.substring(r);
			 if (ext.length() < ext_num) {
				 for (int t = 0,lent = (ext_num-ext.length())/signwordASCII.length(); t < lent; t++) {
					 ext += signwordASCII;
				 }
//				 System.out.println("ext:"+ext+"  "+(ext_num%ext.length()));
				 ext += signwordASCII.substring(0,ext_num-ext.length());
				 
			 }else if(ext.length() > ext_num){
				 ext = ext.substring(0,ext_num);
			 }
			 
			 System.out.println("第二种扩展码："+ext);
			 list.add(top+ext);
		 }
		 return list;
	}
}
