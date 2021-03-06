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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oracle.jdbc.OracleTypes;

public class Test3 {

    Connection conn=null;
    CallableStatement cs=null;//PreparedStatement,Statement
    ResultSet rs;
    private String sepa1 = "[*]";
    private String sepa2 = "[%]";
    
    public void callProc(){
    	ConnectionManager cManager = null;
        try {
        	cManager = new ConnectionManager();
    		conn = cManager.getConnection();
            cs = conn.prepareCall("{call pro_sjzx1()}");
//            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //	年+月+科室编码        项目名称       其他数据
            Map<String,Map<String,String>> map = new HashMap<String, Map<String,String>>();
            String sql = "select * from zm_sjzx_tem";
            ResultSet rs = cManager.executeQuery(sql);
            while(rs!=null&& rs.next()){
            	String year = rs.getString(1);
            	String month = rs.getString(2);
            	String depart_code = rs.getString(3);
            	String xm = rs.getString(4);
            	
            	double mbzb = rs.getDouble(5);
            	double ljwc = rs.getDouble(6);
            	double ljwc_b = rs.getDouble(7);
                
            	double dqmb = rs.getDouble(8);
            	double dqz = rs.getDouble(9);
            	double tqz = rs.getDouble(10);
            	double hqz = rs.getDouble(11);
                
                if(map.get(year+sepa1+month+sepa1+depart_code) != null){
                	Map<String,String> map2 = map.get(year+sepa1+month+sepa1+depart_code);
                	if(map2.get(xm) != null ){
//                		List<String> list2 = new ArrayList<>();
//                		list2 = map2.get(xm);
                	}else{
                		if("医疗收入占比".equals(xm) || "药品收入占比".equals(xm)
                				|| "中医药收入占比".equals(xm)|| "门诊收入占比".equals(xm)|| "住院收入占比".equals(xm)
                				){
                			
                		}else{
                			double ljwcl = round(ljwc/mbzb,2);
                			double ljtqb;
                			if(ljwc_b == 0){
                				ljtqb = 0;
                			}else{
                				ljtqb = round(ljwc/ljwc_b,2);
                			}
                			double sy = round(mbzb - ljwc,2);
                			double yj = round(sy/(12-Integer.parseInt(month)),2);
                			
                			double dqwcl = round(dqz/dqmb,2);
                			double tqdb;
                			if(tqz == 0){
                				tqdb = 0;
                			}else{
                				tqdb = round((dqz-tqz)/tqz,2);
                			}
                			double hb;
                			if(hqz == 0){
                				hb = 0;
                			}else{
                				hb = round((dqz-hqz)/hqz,2);
                			}
                			String str = year+sepa2+month+sepa2+depart_code+sepa2+xm+sepa2+mbzb
                					+sepa2+ljwc+sepa2+ljwcl+sepa2+ljtqb+sepa2+sy+sepa2+yj+sepa2+dqmb
                					+sepa2+dqz+sepa2+dqwcl+sepa2+tqdb+sepa2+hb;
                			map2.put(xm, str);
                		}
                	}
                	map.put(year+sepa1+month+sepa1+depart_code, map2);
                }else{
                	Map<String,String> map2 = new HashMap<>();
                	double ljwcl = round(ljwc/mbzb,2);
        			double ljtqb;
        			if(ljwc_b == 0){
        				ljtqb = 0;
        			}else{
        				ljtqb = round(ljwc/ljwc_b,2);
        			}
        			double sy = round(mbzb - ljwc,2);
        			double yj = round(sy/(12-Integer.parseInt(month)),2);
        			
        			double dqwcl = round(dqz/dqmb,2);
        			double tqdb;
        			if(tqz == 0){
        				tqdb = 0;
        			}else{
        				tqdb = round((dqz-tqz)/tqz,2);
        			}
        			double hb;
        			if(hqz == 0){
        				hb = 0;
        			}else{
        				hb = round((dqz-hqz)/hqz,2);
        			}
        			String str = year+sepa2+month+sepa2+depart_code+sepa2+xm+sepa2+mbzb
        					+sepa2+ljwc+sepa2+ljwcl+sepa2+ljtqb+sepa2+sy+sepa2+yj+sepa2+dqmb
        					+sepa2+dqz+sepa2+dqwcl+sepa2+tqdb+sepa2+hb;
        			map2.put(xm, str);
        			map.put(year+sepa1+month+sepa1+depart_code, map2);
                }
            }
            rs.close();
            //遍历，生成占比
            Map<String,Map<String,String>> map_ = new HashMap<String, Map<String,String>>();
            for (Map.Entry<String,Map<String,String>> entry : map.entrySet()) { 
            	String year = "";
            	String month = "";
            	String depart_code = "";
            	String ydd = entry.getKey();
            	Map<String,String> map1_ = entry.getValue();
            	Map<String,String> map2_ = new HashMap<>();
            	String str1 = "";
            	String str2 = "";
            	if(map1_.get("科室总收入") != null){
            		str1 = map1_.get("科室总收入");
            		String[] strs1 = split(str1,sepa2);
                	year = strs1[0];
                	month = strs1[1];
                	depart_code = strs1[2];
            		if(map1_.get("医疗收入") != null){
                    	str2 = map1_.get("医疗收入");
                    	String xm = "医疗收入占比";
                    	inMap(str1,str2,xm,map_,map2_);
                	}
                	if(map1_.get("药品收入") != null){
                		str2 = map1_.get("药品收入");
                		String xm = "药品收入占比";
                		inMap(str1,str2,xm,map_,map2_);
                	}
                	if(map1_.get("中医药收入") != null){
                		str2 = map1_.get("中医药收入");
                		String xm = "中医药收入占比";
                		inMap(str1,str2,xm,map_,map2_);
                	}
                	if(map1_.get("门诊收入") != null){
                		str2 = map1_.get("门诊收入");
                		String xm = "门诊收入占比";
                		inMap(str1,str2,xm,map_,map2_);
                	}
                	if(map1_.get("住院收入") != null){
                		str2 = map1_.get("住院收入");
                		String xm = "住院收入占比";
                		inMap(str1,str2,xm,map_,map2_);
                	}
                	//门诊次均费用
                	if(map1_.get("门诊收入") != null && map1_.get("门诊挂号量") != null){
                		str1 = map1_.get("门诊收入");
                		str2 = map1_.get("门诊挂号量");
                		String xm = "门诊次均费用";
                		inMap(str1,str2,xm,map_,map2_);
                	}
                	//手术例数占比
                	if(map1_.get("手术例数") != null){
                		String sqlSSLS = "select sum(MBZB) mbzb,sum(ljzb) ljwc,sum(MBZB-ljzb) symb,sum(MBZB-ljzb/8) yj,sum(dqmb) dqmb,sum(dqz) dqz from zm_sjzx_tem where year = ? and month = ? and xm = ?";
                		Object[] params = {year,month,"手术例数"};
                		ResultSet rsSSLS = cManager.WexecuteQuery(sqlSSLS, params);
                		if(rsSSLS.next()){
                			double mbzb = rsSSLS.getDouble("mbzb");
                			double ljwc = rsSSLS.getDouble("ljwc");
                			double symb = rsSSLS.getDouble("symb");
                			double yj = rsSSLS.getDouble("yj");
                			double dqmb = rsSSLS.getDouble("dqmb");
                			double dqz = rsSSLS.getDouble("dqz");
                			str1 = year+sepa2+month+sepa2+depart_code+sepa2+"手术例数(总)"+sepa2+mbzb
                					+sepa2+ljwc+sepa2+""+sepa2+""+sepa2+symb+sepa2+yj+sepa2+dqmb
                					+sepa2+dqz+sepa2+""+sepa2+""+sepa2+"";
                			str2 = map1_.get("手术例数");
                    		String xm = "手术例数占比";
                    		inMap(str1,str2,xm,map_,map2_);
                		}
                		rsSSLS.close();
                	}
                	map_.put(year+sepa1+month+sepa1+depart_code, map2_);
            	}else{
            		
            	}
            }
            
            
            //遍历，放在list中
            List<String> listE = new ArrayList<>();
            List<String> listE1 = new ArrayList<>();
            
            for (Map.Entry<String,Map<String,String>> entry : map.entrySet()) { 
            	String ydd = entry.getKey();
            	Map<String,String> map1_ = entry.getValue();
            	for (Map.Entry<String,String> entry1 : map1_.entrySet()) { 
                	String xm = entry1.getKey();
                	String str = entry1.getValue();
                	listE.add(str);
            	}
            }
            for (Map.Entry<String,Map<String,String>> entry : map_.entrySet()) { 
            	String ydd = entry.getKey();
            	Map<String,String> map1_ = entry.getValue();
            	for (Map.Entry<String,String> entry1 : map1_.entrySet()) { 
            		String xm = entry1.getKey();
            		String str = entry1.getValue();
            		listE.add(str);
            	}
            }
            
            //遍历list，排序
            Map<Integer,String> mapE = new HashMap<>();
            for(String attr : listE) {
            	String[] attrs = split(attr,sepa2);
            	if("科室总收入".equals(attrs[3])){
            		mapE.put(1, attr);
            	}else if("医疗收入".equals(attrs[3])){
            		mapE.put(2, attr);
            	}else if("药品收入".equals(attrs[3])){
            		mapE.put(3, attr);
            	}else if("医疗收入占比".equals(attrs[3])){
            		mapE.put(4, attr);
            	}else if("药品收入占比".equals(attrs[3])){
            		mapE.put(5, attr);
            	}else if("中医药收入".equals(attrs[3])){
            		mapE.put(6, attr);
            	}else if("中医药收入占比".equals(attrs[3])){
            		mapE.put(7, attr);
            	}else if("门诊收入".equals(attrs[3])){
            		mapE.put(8, attr);
            	}else if("住院收入".equals(attrs[3])){
            		mapE.put(9, attr);
            	}else if("门诊收入占比".equals(attrs[3])){
            		mapE.put(10, attr);
            	}else if("住院收入占比".equals(attrs[3])){
            		mapE.put(11, attr);
            	}else if("门诊挂号量".equals(attrs[3])){
            		mapE.put(12, attr);
            	}else if("门诊就诊量".equals(attrs[3])){
            		mapE.put(13, attr);
            	}else if("门诊医保患者量".equals(attrs[3])){
            		mapE.put(14, attr);
            	}else if("未就诊人数".equals(attrs[3])){
            		mapE.put(15, attr);
            	}else if("零消费门诊量".equals(attrs[3])){
            		mapE.put(16, attr);
            	}else if("门诊首诊率".equals(attrs[3])){
            		mapE.put(17, attr);
            	}else if("门诊复诊率".equals(attrs[3])){
            		mapE.put(18, attr);
            	}else if("门诊次均费用".equals(attrs[3])){
            		mapE.put(19, attr);
            	}else if("门诊中医药收入占比".equals(attrs[3])){
            		mapE.put(20, attr);
            	}else if("入院患者量".equals(attrs[3])){
            		mapE.put(21, attr);
            	}else if("出院人数".equals(attrs[3])){
            		mapE.put(22, attr);
            	}else if("医保患者量".equals(attrs[3])){
            		mapE.put(23, attr);
            	}else if("医保患者收入".equals(attrs[3])){
            		mapE.put(24, attr);
            	}else if("医保患者次均费用".equals(attrs[3])){
            		mapE.put(25, attr);
            	}else if("出院患者次均费用".equals(attrs[3])){
            		mapE.put(26, attr);
            	}else if("本科室收容人数".equals(attrs[3])){
            		mapE.put(27, attr);
            	}else if("本科室收容率".equals(attrs[3])){
            		mapE.put(28, attr);
            	}else if("急诊收容人数".equals(attrs[3])){
            		mapE.put(29, attr);
            	}else if("转入住院患者".equals(attrs[3])){
            		mapE.put(30, attr);
            	}else if("转出患者量".equals(attrs[3])){
            		mapE.put(31, attr);
            	}else if("实际占床日".equals(attrs[3])){
            		mapE.put(32, attr);
            	}else if("平均患者在院日".equals(attrs[3])){
            		mapE.put(33, attr);
            	}else if("床位使用率".equals(attrs[3])){
            		mapE.put(34, attr);
            	}else if("住院患者中医药收入占比".equals(attrs[3])){
            		mapE.put(35, attr);
            	}else if("手术例数".equals(attrs[3])){
            		mapE.put(36, attr);
            	}else if("手术例数占比".equals(attrs[3])){
            		mapE.put(37, attr);
            	}
            }
            Map<Integer,String> mapE_ = sortMapByKey(mapE,0);

          //遍历map，放入表中
            for (Map.Entry<Integer,String> entry : mapE_.entrySet()) {
            	int m = entry.getKey();
            	String str = entry.getValue();
            	//放在表中
            	String[] strs = split(str,sepa2);
            	String id = "";
            	String seqSql = "select ZM_ZBSJK_INFO1_SEQ.nextval as id from dual";
				ResultSet seqRs = cManager.executeQuery(seqSql);
				if (seqRs != null) {
					if (seqRs.next()) {
						id = seqRs.getString("id");
					}
				}
				seqRs.close();
            	String year = strs[0];
            	String month = strs[1];
            	String depart_code = strs[2];
            	String project = strs[3];
            	if(m == 4 || m == 5 ||m == 7 ||m == 10 ||m == 11){
            		double mbzb = Double.parseDouble(strs[4]);
	            	double ljwc = Double.parseDouble(strs[5]);
	            	double symb = Double.parseDouble(strs[8]);
	            	double yj = Double.parseDouble(strs[9]);
	            	double dqmb = Double.parseDouble(strs[10]);
	            	double dqz = Double.parseDouble(strs[11]);
	            	String sqlIns = "insert into zm_zbsjk_info1(ID,YEAR,MONTH,DEPARTOPCODE,PROJECT,MBZB,LJWC,LJWCL,LJTQDB,SYMB,YJ,DQMB,DQZ,DQWCL,TQDB,HB) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	            	Object[] params = {id,year,month,depart_code,project,
	            			mbzb,ljwc,"","",symb,yj,dqmb,dqz,"","",""};
	            	int rnum = cManager.WexecuteUpdate(sqlIns, params);
	            	if(rnum == 1){
	            	}
            	}else if(m == 20 || m == 35){
            		
            	}else{
            		double mbzb = Double.parseDouble(strs[4]);
                	double ljwc = Double.parseDouble(strs[5]);
                	double ljwcl;
                	if(isNumber(strs[6])){
                		ljwcl = Double.parseDouble(strs[6]);
                	}else{
                		ljwcl = 0;
                	}
                	double ljtqdb;
                	if(isNumber(strs[7])){
                		ljtqdb = Double.parseDouble(strs[7]);
                	}else{
                		ljtqdb = 0;
                	}
                	double symb = Double.parseDouble(strs[8]);
                	double yj = Double.parseDouble(strs[9]);
                	double dqmb = Double.parseDouble(strs[10]);
                	double dqz = Double.parseDouble(strs[11]);
                	double dqwcl;
                	if(isNumber(strs[12])){
                		dqwcl = Double.parseDouble(strs[12]);
                	}else{
                		dqwcl = 0;
                	}
                	double tqdb;
                	if(isNumber(strs[12])){
                		tqdb = Double.parseDouble(strs[13]);
                	}else{
                		tqdb = 0;
                	}
                	double hb;
                	if(isNumber(strs[12])){
                		hb = Double.parseDouble(strs[14]);
                	}else{
                		hb = 0;
                	}
                	System.out.println(project+"--"+mbzb+"--"+ljwc+"--"+ljwcl+"--"+ljtqdb+"--"+symb+"--"+yj+"--"+dqmb+"--"+dqz+"--"+dqwcl+"--"+tqdb+"--"+hb);
                	String sqlIns = "insert into zm_zbsjk_info1(ID,YEAR,MONTH,DEPARTOPCODE,PROJECT,MBZB,LJWC,LJWCL,LJTQDB,SYMB,YJ,DQMB,DQZ,DQWCL,TQDB,HB) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                	Object[] params = {id,year,month,depart_code,project,
                			mbzb,ljwc,ljwcl,ljtqdb,symb,yj,dqmb,dqz,dqwcl,tqdb,hb};
                	int rnum = cManager.WexecuteUpdate(sqlIns, params);
                	if(rnum == 1){
                	}
            	}
            	
                	
                
            }
            //删除临时表
            String sqlDel = "delete from zm_sjzx_tem";
            int re = cManager.executeUpdate(sqlDel);
            if(re >= 0){
            	
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
        	cManager.closeConn();
        }
    }
    
    public  Map sortMapByKey(Map map,int type) {
		if (map == null || map.isEmpty()) {
			return map;
		}
		Collection<String> keyset= map.keySet();  
        List<String> list = new ArrayList<String>(keyset);  
          
        //对key键值按字典升序排序  
        Collections.sort(list);  
        
        Map newMap = new LinkedHashMap();  
	    if (type==0) {
		    for (int i = 0; i < list.size(); i++) {  
		        newMap.put(list.get(i), map.get(list.get(i)));  
		    }
		}else{
			for (int i = list.size()-1; i >=0; i--) {  
				newMap.put(list.get(i), map.get(list.get(i)));  
		    }
		}
	    return newMap;
	}

	public static void main(String[] args) {
		Test3 t3 = new Test3();
	
		t3.callProc();
	}
	//四舍五入
	public  double round(double yuan,int length){
		if(length<0){
			length = 0;
		}
		String my = String.format("%."+length+"f", yuan);
		return Double.parseDouble(my);
	}
	public  String[] split(String source, String div) {
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
	public  boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+\\.?[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
	public void inMap(String str1,String str2,String xm,Map<String,Map<String,String>> map_,Map<String,String> map2_){
    	String year = "";
    	String month = "";
    	String depart_code = "";
    	String[] strs1 = split(str1,sepa2);
    	String[] strs2 = split(str2,sepa2);
    	year = strs1[0];
    	month = strs1[1];
    	depart_code = strs1[2];
    	
    	double mbzb2 = round(Double.parseDouble(strs2[4])/Double.parseDouble(strs1[4]),2);
		double ljwc2 = round(Double.parseDouble(strs2[5])/Double.parseDouble(strs1[5]),2);
		double sy2 = round(Double.parseDouble(strs2[8])/Double.parseDouble(strs1[8]),2);
		double yj2 = round(Double.parseDouble(strs2[9])/Double.parseDouble(strs1[9]),2);
		double dqmb2 = round(Double.parseDouble(strs2[10])/Double.parseDouble(strs1[10]),2);
		double dqz2 = round(Double.parseDouble(strs2[11])/Double.parseDouble(strs1[11]),2);
		
		String value2 = year+sepa2+month+sepa2+depart_code+sepa2+xm+sepa2+mbzb2
				+sepa2+ljwc2+sepa2+""+sepa2+""+sepa2+sy2+sepa2+yj2+sepa2+dqmb2
				+sepa2+dqz2+sepa2+""+sepa2+""+sepa2+"";
		map2_.put(xm, value2);
		
//		map_.put(year+sepa1+month+sepa1+depart_code, map2_);
	}
}
