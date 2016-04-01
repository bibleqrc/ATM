
package atm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dataAccess 
{
	 private static Connection conn;
   private Statement stmt; 
   

 
 

   public dataAccess() //���캯�������������ݿ�
   {
	     String url="jdbc:mysql:///atm";
		 String user="root";
		 String psw="";

		
	  try
	  {
	  	Class.forName("com.mysql.jdbc.Driver");
	  	 conn = DriverManager.getConnection(url,user,psw);
		  stmt=conn.createStatement();  
	  }
	  catch(Exception e1)
	  {
	  	System.out.println(e1.toString());
	  }    
	 
   }

//返回某一特定的数据集
  public ResultSet getData(String sqlstr)//���ز�ѯ�����ݼ�
  {
	  try
	  {
	  	ResultSet rs=this.stmt.executeQuery(sqlstr);
	  	return rs;
	  }
	  catch(Exception e)
	  {
	  	System.out.println(e.toString());
	  	return null;
	  }
  }

  

//返回某一特定数据集的行数
  public  int getRowcount(String sqlstr)
  {
  	int i=0;
  	try
  	{
	  	ResultSet rs=this.getData(sqlstr);
	  	while(rs.next()) i++;
  	}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			return i;
		}  	
  }

//执行某一特定无返回的sql
  public boolean exeSql(String sqlstr)//执行无返回sql
  {
  	try
  	{
  		stmt.execute(sqlstr);
  		return true;  		
  	}
  	catch(Exception e)
  	{  		
  		System.out.println(e.toString());
  		return false;
  	}
  }
  

//延时
  public void sleep(int second)
  {
  	try
  	{
  		Thread th=new Thread();
  		th.sleep(second*1000);
  	}
  	catch(Exception e)
  	{
  		System.out.println(e.toString());
  	}
  }
  
  protected void finalize()//析构函数
	{
		try
		{
			if(stmt!=null) stmt.close();
			if(conn!=null) conn.close();
		
		}
		catch(Exception e)
		{}
	} 

   public static void main(String[] args)
   {
	   dataAccess  a=new dataAccess();
	   
	   //int zz= getRowcount("select * from one");
	   //System.out.println(zz);
   }
}
