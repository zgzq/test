package test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oracle.jdbc.OracleTypes;

public class TestOA {
    public void callProc(){
    	ConnectionManagerOA cManager = null;
    	ConnectionManager zManager = null;
        try {
        	cManager = new ConnectionManagerOA();
        	zManager = new ConnectionManager();
            
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH)+1;
            int day = now.get(Calendar.DAY_OF_MONTH);
            if(day == 1){
            	if(month == 1){
            		String sql1 = "select sum(kpivalue) num from kpi_value where orgid = ? and kpiid = ? and SUBSTR(pdate,0,6) = ?";
                    String orgid = "1";
                    String kpiid = "328";
                    String yearMonth;
                    String value = "";
                    
                    yearMonth = (year-1)+"12";
                    
                    Object[] params1 = {orgid,kpiid,yearMonth};
                    ResultSet rs1 = zManager.WexecuteQuery(sql1, params1);
                    if(rs1.next()){
                    	value = rs1.getString("num")==null?"0":rs1.getString("num");
                    }
                    rs1.close();
                    zManager.closeRs();
                	String sql2 = "update  formmain_5635 set field0004 = ? where field0002 = ? and Datepart(Yy,field0001) = ? and Datepart(Mm,field0001)=?";
                	String hos = "8507457914790233028";
                	Object[] params2 = {value,hos,year-1,12};
                	int num = cManager.WexecuteUpdate(sql2, params2);
                	if(num  > 0){
                	}
            	}else{
            		String sql1 = "select sum(kpivalue) num from kpi_value where orgid = ? and kpiid = ? and SUBSTR(pdate,0,6) = ?";
            		String orgid = "1";
            		String kpiid = "328";
            		String yearMonth;
            		String value = "";
            		if(month < 10){
            			yearMonth = year+"0"+(month-1);
            		}else{
            			yearMonth = year+""+(month-1);
            		}
            		Object[] params1 = {orgid,kpiid,yearMonth};
            		ResultSet rs1 = zManager.WexecuteQuery(sql1, params1);
            		if(rs1.next()){
            			value = rs1.getString("num")==null?"0":rs1.getString("num");
            		}
            		rs1.close();
            		zManager.closeRs();
            		String sql2 = "update  formmain_5635 set field0004 = ? where field0002 = ? and Datepart(Yy,field0001) = ? and Datepart(Mm,field0001)=?";
            		String hos = "8507457914790233028";
            		Object[] params2 = {value,hos,year,month-1};
            		int num = cManager.WexecuteUpdate(sql2, params2);
            		if(num  > 0){
            		}
            	}
            }else{
            	
            }
            String sql1 = "select sum(kpivalue) num from kpi_value where orgid = ? and kpiid = ? and SUBSTR(pdate,0,6) = ?";
            String orgid = "1";
            String kpiid = "328";
            String yearMonth;
            String value = "";
            if(month < 10){
            	yearMonth = year+"0"+month;
            }else{
            	yearMonth = year+""+month;
            }
            Object[] params1 = {orgid,kpiid,yearMonth};
            ResultSet rs1 = zManager.WexecuteQuery(sql1, params1);
            if(rs1.next()){
            	value = rs1.getString("num")==null?"0":rs1.getString("num");
            }
            rs1.close();
            zManager.closeRs();
        	String sql2 = "update  formmain_5635 set field0004 = ? where field0002 = ? and Datepart(Yy,field0001) = ? and Datepart(Mm,field0001)=?";
        	String hos = "8507457914790233028";
        	Object[] params2 = {value,hos,year,month};
        	int num = cManager.WexecuteUpdate(sql2, params2);
        	if(num  > 0){
        	}
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
        	cManager.closeConn();
        }
    }
    
    

	public static void main(String[] args) {
		TestOA t3 = new TestOA();
	
		t3.callProc();
	}
}
