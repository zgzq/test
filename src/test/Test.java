package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		/*String content1 = "1,2,3,4,5,6,7,8,9,10,11";
		String phone1 = "11,22,33,44,55,66,77,88,99,1010,1111";
		String[] contentArr = content1.split(",");
		String[] phoneArr = phone1.split(",");
		String locationNum = "";
		String smsid = "100a";
		for (int i = 0; i < contentArr.length; i++) {
			String phone = phoneArr[i];
			String content = contentArr[i];
//			String oper = phoneOper[i];
//			String oper = "-1";
			locationNum = i+"";
			
			for (int j = 0,len=4-locationNum.length(); j < len; j++) {
				locationNum = "0"+locationNum;
			}
			String smsid2 = smsid+locationNum;
			System.out.println(smsid2);
			//���͵���Ϣ����
			
		}
		jingdu();*/
		//StringInt();
		//zf();
		//subString();
		//dayAdd();
		//listStr();
		//compareDate();
//		splitStr();
		//month();
//		Date d = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println("��ǰʱ�䣺" + sdf.format(d));
//		String curDate = sdf.format(d);
//		int i = compare_date(curDate,"2017-06-15");
		//calander();
//		mt();
		geneNum(11);
	}
	public static void geneNum(int num){
		 Calendar now = Calendar.getInstance();
         int year = now.get(Calendar.YEAR);
         int month = now.get(Calendar.MONTH)+1;
         int day = now.get(Calendar.DAY_OF_MONTH);
         System.out.println("年: " + now.get(Calendar.YEAR));  
         System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");  
         System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
	}
	public static void mt(){
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println(day);
	}
	public static void maptest(){
		//�����
		Map<String,String> map1 = new HashMap<>();
		Map<String,String> map2 = new HashMap<>();
		map1.put("111", "aaa");
		map1.put("222", "bbb");
		map1.put("333", "ccc");
		map2.put("444", "eee");
		map2.put("555", "ddd");
		
		for (Map.Entry<String,String> entry : map1.entrySet()) { 
        	for (Map.Entry<String,String> entry1 : map2.entrySet()) { 
            	String xm = entry1.getKey();
            	String str = entry1.getValue();
            	map1.put(xm, str);
            }
        }
		for (Map.Entry<String,String> entry : map1.entrySet()) { 
        	 
            String xm = entry.getKey();
            String str = entry.getValue();
            System.out.println(xm);
        }
		
		
	}
	public static void calander(){
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.MINUTE));
		cal.add(Calendar.MINUTE, -2);//1����ǰ
		System.out.println(cal.get(Calendar.MINUTE));
	}
	public static void month(){
		Calendar now = Calendar.getInstance();
		String curMonth = (now.get(Calendar.MONTH) + 1)+"";
		System.out.println(curMonth);
	}
	public static int compare_date(String DATE1, String DATE2) {
	       
	       
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 ��dt2ǰ");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1��dt2��");
                return -1;
            } else {
            	System.out.println("dt1===dt2");
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

	public static void splitStr(){
//		String str = "2017-09-09";
//		String[] strs = str.split("-");
//		for( int i = 0 ; i < strs.length ; i ++){
//			System.out.println(strs[i]);
//		}
		String str = "&a=b&a=c&a=d&a=e&a=f";
		String[] strs = str.split("&");
		for( int i = 0 ; i < strs.length ; i ++){
			System.out.println(strs[i]);
		}
	}
	public static void compareDate(){
		String str1="2015-02-08";
        String str2="2015-02-09";
        int res=str1.compareTo(str2);
        if(res>0)
            System.out.println("str1>str2");
        else if(res==0)
            System.out.println("str1=str2");
        else
            System.out.println("str1<str2");
        
	}
	public static void subString(){
		String str = "qwertyuiop";
		String str1 = str.substring(1, 3);
		System.out.println(str1);
	}
	public static void jingdu(){
		Double a = null;
		System.out.println(a.MAX_VALUE+"");
	}
	public static void StringInt(){
		int i = 20;
		String str = "5001";
		String strs = str + i;
		System.out.println(strs);
	}
	public static void zf(){
		int i = 20;
		int j = -i;
		long a = 20000;
		long b = -a;
		System.out.println(j);
		System.out.println(b);
	}
	public static void dayAdd(){
		Date date = new Date();
		date.setDate(date.getDate()+1);
		System.out.println(date);
	}
	
	public static void listStr(){
		List<String> blockChannelList = new ArrayList<String>();
		int a = 2;
		int b = 3;
		blockChannelList.add(a+","+b);
		for(int i = 0; i < blockChannelList.size();i++){
			System.out.println(blockChannelList.get(i));
		}
	}
	
}
