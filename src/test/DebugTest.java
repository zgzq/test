package test;

public class DebugTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("main执行");
		fun1();
	}
	public static void fun1(){
		System.out.println("fun1执行");
		fun2();
	}
	public static void fun2(){
		System.out.println("fun2执行");
		fun3();
	}
	public static void fun3(){
		System.out.println("fun3执行");
		fun4();
	}
	public static void fun4(){
		System.out.println("fun4执行");
	}

}
