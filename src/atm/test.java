package atm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {

	
	public static void main(String[] args)
	{
		String url="jdbc:mysql:///atm";
		 String user="root";
		 String psw="";
		 
		 Connection conn;
		  Statement stmt;
		 
		  
		  try
		  {
		  	Class.forName("com.mysql.jdbc.Driver");
		  	 conn = DriverManager.getConnection(url,user,psw);
			  stmt=conn.createStatement();  
		  
		   String query = "select * from one";
		  ResultSet result = stmt.executeQuery(query);
		  System.out.println("Student表数据如下：");
		  System.out.println("---------------------------------");
		  System.out.println("学号"+" "+"姓名"+" "+"数学成绩");
		  System.out.println("---------------------------------");
		  int number;
		  int name;
		  String math;
		  float zzz;
		  
		  while(result.next()){
		  number = result.getInt("id");
		  name = result.getInt("gid");
		  math = result.getString("password");
		  zzz=result.getFloat("money");
		  
		  System.out.println(number + " " + name + " " + math + " " + zzz);
		  
		  }
		  }
		  catch(Exception e1)
		  {
		  	System.out.println(e1.toString());
		  }    
		  
		  
		  
		 
		  
	}
}
