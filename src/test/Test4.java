package test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

public class Test4 {

    Connection conn=null;
    CallableStatement cs=null;//PreparedStatement,Statement
    CallableStatement cs1=null;//PreparedStatement,Statement
    ResultSet rs;
    public static void main(String[] args) {
		Test4 t4 = new Test4();
	
		t4.callProc();
	}
    public void callProc(){
    	ConnectionManager cManager = null;
        try {
        	cManager = new ConnectionManager();
    		conn = cManager.getConnection();
            cs = conn.prepareCall("{call pro_surgery()}");
            cs.execute();
//            cs1 = conn.prepareCall("{call pro_surgery_col()}");
//            cs.registerOutParameter(1, OracleTypes.CURSOR);
//            cs1.execute();
            
            
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
        	cManager.closeConn();
        }
    }
    
    
}
