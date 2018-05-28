package test;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		stringTest("我爱你中国【你好】");
		
	}
	public static void stringTest(String SameContent){
		String sign = "";
		String content = "";
		if(SameContent.startsWith("【")){
			
			int i = SameContent.indexOf("】");
			sign = SameContent.substring(0, i+1);
			content = SameContent.substring(i+1);
		}else{
			int i = SameContent.indexOf("【");
			sign = SameContent.substring(i);
			content = SameContent.substring(0,i);
		}
		System.out.println(sign);
		System.out.println(content);
	}

}
