package com.oe.dao;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class LoginDao {

	public String doFetch(Connection conn)throws Exception{
	//	boolean insertFlag = false;
		//PreparedStatement ps =null;
		ResultSet rs = null;
		Statement stmt=null;
		
		//int i = 0;
		try{
			
			
				String sql="select id,c.name,invdate,amount,tax,total,closed,ship_via,note from invheader i,clients c where  c.client_id=i.client_id order by i.id";
				stmt =conn.createStatement(); //connection to statement
				rs = stmt.executeQuery(sql); 
				System.out.println("in dao");
				  List<Map<String, Object>>  contents = getEntitiesFromResultSet(rs);  //get 
	                ObjectMapper mapper = new ObjectMapper();   // Jack json library

	                String json = mapper.writeValueAsString(contents);
	              //  System.out.println(json);
	                return  json;
			}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
			

	}
	
	
	 protected static List<Map<String, Object>> getEntitiesFromResultSet(ResultSet resultSet) throws SQLException 
	    { System.out.println("in dao2222");
	        ArrayList<Map<String, Object>> entities = new ArrayList<Map<String, Object>>();
	        while (resultSet.next()) {
	            entities.add(getEntityFromResultSet(resultSet)); //add map into List
	        }
	      //  System.out.println(entities);
	        return entities; 
	        
	        //final output
	        /* output:[{age=25, name=Dinesh},
	         *  {age=29, name=Aarya}, 
	         *  {age=26, name=arjun}, 
	         *  {age=30, name=Drona}]
	        */
	    }
		
		private static Map<String, Object> getEntityFromResultSet(ResultSet rs1) throws SQLException 
		{
			 ResultSetMetaData metaData = rs1.getMetaData(); //get meta data from resultset
		        int columnCount = metaData.getColumnCount(); // get no. of columns by getColumnCount() function in metadata 
		        Map<String,Object> resultsMap = new HashMap<String,Object>(); // init hashmap
		        for (int i = 1; i <= columnCount; ++i) { 
		            String columnName = metaData.getColumnName(i).toLowerCase(); //get column data
		            Object object = rs1.getObject(i);
		            resultsMap.put(columnName, object);
		        } //runs until column count
		       // System.out.println(columnCount);
		        return resultsMap;	
		        /* output:
		        *  {age=25, name=Dinesh}
				*{age=29, name=Aarya}
				*	{age=26, name=arjun}
				*	{age=30, name=Drona} 
				**/
		}
	}
