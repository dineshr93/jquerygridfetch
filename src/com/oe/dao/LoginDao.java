package com.oe.dao;
import java.sql.Connection;


//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class LoginDao {

	public String doFetch(Connection conn)throws Exception{
	//	boolean insertFlag = false;
		//PreparedStatement ps =null;
		ResultSet rs = null;
		Statement stmt=null;
		// connection = null;
		//int i = 0;
		try{
			
			
				String sql="select id,c.name,invdate,amount,tax,total,closed,ship_via,note from invheader i,clients c where  c.client_id=i.client_id order by i.id";
				stmt =conn.createStatement(); //connection to statement
				rs = stmt.executeQuery(sql); 
				System.out.println("in fetch dao");
				 //get 
	             
				  
				   List<Map<String, Object>>  contents = getEntitiesFromResultSet(rs); 
				   ObjectMapper mapper = new ObjectMapper();     // Jack json librarys
	               String json = mapper.writeValueAsString(contents);
	             //  System.out.println(json);
	               return  json;
			}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
			   
		/*return null;*/
			

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
		public void doInsert(Connection conn,String nam,String indate, String amont, String tx, String tot,String closd, String shipvia, String not) throws Exception {
			
			ResultSet rs1 = null;
			
			Statement st=null;
			Statement st1=null;
			Statement d=null;
			// TODO Auto-generated method stub
			try{
				/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
				String dateInString = indate;
		 Date date = sdf.parse(dateInString);
				System.out.println(date);*/
				String s= "SELECT client_id FROM clients OFFSET random()*(select count(*) from clients) LIMIT 1";
				d=conn.createStatement();
		 rs1 = d.executeQuery(s);
		 int val1 = random1(rs1);
	
		
		//  =  (int)rs1; 
				System.out.println("came above sql");
				/*to_date("+date+",'YYYY-MM-DD')*///to_date('10Apr96', 'DDMonYY')
			System.out.println("after 1st query");
/*				String sql1="insert  into clients(name) values("+nam+")";
*/				String sql1="insert into clients(name) values ('"+nam+"');";
				
				System.out.println("after 2nd query");
				String sql= "with client_id as ( select client_id from clients WHERE name = '"+nam+"') insert into invheader (invdate, client_id, amount, tax, total, closed, ship_via, note) values (to_date('"+indate+"', 'DD-mon-YYYY'),(select client_id from client_id),'"+amont+"','"+tx+"','"+tot+"','"+closd+"','"+shipvia+"','"+not+"')";
			/*			"insert  into invheader(invdate,client_id,amount,tax,total,closed,ship_via,note)values(to_date('"+indate+"', 'DD-mon-YYYY'),'"+val1+"','"+amont+"','"+tx+"','"+tot+"','"+closd+"','"+shipvia+"','"+not+"')";
			*/		/*"insert  into invheader(id,invdate,client_id,amount,tax,total,closed,ship_via,note) values(100002,to_date('1963-09-01', 'YYYY-MM-DD'),100000,100.00,20.00,120.00,TRUE,'tnt','note 1')";
					*///"insert  into invheader(id,invdate,client_id,amount,tax,total,closed,ship_via,note) values('',to_date('1963-09-01', 'YYYY-MM-DD'),"+i+","+amont+","+tx+","+tot+","+closd+","+shipvia+","+not+")";
			
				
				
				st =conn.createStatement();
				st1 =conn.createStatement();
				//Statement stmt1 = conn.createStatement();//connection to statement
				System.out.println("above execute1");
				st1.executeUpdate(sql1);
				
				System.out.println("above execute");
				st.executeUpdate(sql);
				
				System.out.println("inserted dao");
				conn.setAutoCommit(true);
			}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
			
			/*	String in=indate;
			
			String reverse = new StringBuffer(in).reverse().toString();
			System.out.println(reverse);
		
			System.out.println(reverse);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
			String dateInString = indate;
	 Date date = sdf.parse(dateInString);
			System.out.println(date);*/
			
			
			/*String it=i; 
			String n=nam; 
			String in=indate; 
			String a=amont; 
			String t=tx;
			String tota=tot; 
			String closed=closd; 
			String ship_via=shipvia; 
			String note=not;
			to_date("+date+",'YYYY-MM-DD')*/
			// TODO Auto-generated method stub
			
		}




		private int random1(ResultSet rs1) throws SQLException {
			// TODO Auto-generated method stub
			int val = 0;
			 while(rs1.next())
				  val =rs1.getInt("client_id");
			return val;
		}
	}
