package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileTest {
/**
 * ÎÄ¼þ±ßÐ´±ß¶Á
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//wri();
		try {
			List<String> list1 = new ArrayList<String>();
			List<String> list2 = new ArrayList<String>();
			List<String> list3 = new ArrayList<String>();
			//FileInputStream in=new FileInputStream(new File("D:\\a.txt"));
			/*File des =new File("D:\\b.txt");
			if(!des.exists()){
			    des.createNewFile();
			}*/
			//FileOutputStream out=new FileOutputStream(des);
			InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\a.txt"), "GBK");
			InputStreamReader isr2 = new InputStreamReader(new FileInputStream("D:\\b.txt"), "GBK");
			BufferedReader br = new BufferedReader(isr);
			BufferedReader br2 = new BufferedReader(isr2);
        	String line = null;
        	String line1 = null;
			byte [] bytes=new byte[1024];
			int len;
			while((line=br.readLine())!=null){
				//System.out.println(line);
				
				list1.add(line);
			}
			while((line1=br2.readLine())!=null){
				//System.out.println(line);
				
				list2.add(line1);
			}
			for(String s:list1){
				//System.out.println(s);
				for(String ss : list2){
					if(ss.equals(s)){
						System.out.println(ss);	
						list3.add(ss);
					}
				}
			}
			for(String s: list3){
				list1.remove(s);
			}
			for(String s: list1){
				System.out.println(s);
			}
			br.close();
			br2.close();
			//out.flush();
			//out.close();
			//in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void wri(){
		FileOutputStream fos = null;
		File des = null;
		try {
		fos = new FileOutputStream("D:\\a.txt");
		des=new File("D:\\b.txt");
		FileInputStream in=new FileInputStream(new File("D:\\a.txt"));
		FileOutputStream out=new FileOutputStream(des);
		for(int i = 0; i < 100; i++){
			String s = "football"+"\r\n";
				fos.write(s.getBytes());
				if(!des.exists()){
				    des.createNewFile();
				}
				byte [] bytes=new byte[1024];
				int len;
				while((len=in.read(bytes))!=-1){
					System.out.println(len);
				    out.write(bytes, 0, len);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		out.flush();
		out.close();
		in.close();
			} catch (Exception ex) {
				
				ex.printStackTrace();
				
			} finally {
				
				if (fos != null) {
					
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}
				
			}
			
		
	}

}
