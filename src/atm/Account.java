
package atm;

import java.sql.*;

public class Account 
{
	
	dataAccess das=new dataAccess();
//修改密码

   public int changePwd(String gid,String pwd,String oldPwd,String newPwd,String newPwd2) 
   {
			 if(newPwd.length()==0 || newPwd.trim().equals("")) return -3;// 新密码不为空
			 else if(oldPwd.equals(pwd))
			 {
			 		if(newPwd.equals(newPwd2))
			 		{
			 			das.exeSql("update one set password='"+newPwd+"' where gid='"+gid+"'");
			 			return 1;// 密码修改成功
			 		}
			 		else
			 		{
			 			return -2;// 两次输入不同
			 		} 		
			 		
			 }
			 else
			 {
			 		return -1;// 旧密码输入错误
			 }			 		 	
	 }
   


//转账操作

 public float  transfer(String gid,String tran_account,String tran_RMB)
 {
	 float rs,rs1,backrs=1;
 	 String accsql="select * from one where gid='"+tran_account+"'";
 	 //String gidsql="select * from guest where gid='"+gid+"'";
 	 rs=das.getRowcount(accsql);
 	 rs1=this.queryBalance(gid);
 	 if (tran_RMB.length() == 0
			|| tran_RMB.trim().equals("")
			|| tran_account.length() == 0
			|| tran_account.trim().equals(""))
 		 backrs=-1; //输入信息不为空
	 	//return rs;
 	 else if(rs==0)
	 		backrs=-2;  //系统里面没有改账户信息
 	 else if(rs1<Float.parseFloat(tran_RMB))
 		 backrs=-3;
	 return backrs;
 }
   
  
 
//用于验证用户ID

   public boolean checkAccount(String tid) 
   {
     String ssql;     
     ssql="select * from one where gid='"+tid+"'";     
     if(das.getRowcount(ssql)>0)// 合法ID
     {
     	 return true;
     }
     else// 非法ID
     {
     	 return false;
     }
   }
   


//验证用户密码
   public boolean checkPwd(String tid,String tpwd) 
   {
     String ssql;
     ssql="select * from one where password='"+tpwd+"' and gid='"+tid+"'";
     if(das.getRowcount(ssql)>0)// 合法密码
     {
     	 return true;
     }
     else// 非法密码
     {
     	 return false;
     }
   }


   
//查询余额
	 public float queryBalance(String gid)
	 {
	 	 ResultSet rs;
	 	 String ssql="select * from one where gid='"+gid+"'";
	 	 float balance=0;
	 	 try
	 	 {
	 	 	 rs=das.getData(ssql);
	 	 	 rs.next();
	 	 	 balance=rs.getFloat("money");
	 	 }
	 	 catch(Exception e)
	 	 {
	 	 	 System.out.println(e.toString());
	 	 }
	 	 finally
	 	 {
	 	 	 return balance;
	 	 }
	 	 
	 }
	 
	 
//扣除取款数目
   public boolean reduceMoney(String tgid,float money) 
   {
     String ssql;
     if(this.queryBalance(tgid)<money) return false;     
     ssql="update one set money=money-"+money+" where gid='"+tgid+"'";
     if(das.exeSql(ssql)) return true; // 扣款成功
   	 else return false; // 扣款失败
   }
   

   
//加入存款
    public boolean addMoney(String tgid,float money) 
    {
      String ssql;
      if(this.queryBalance(tgid)<money) return false;     
      ssql="update one set money=money+"+money+" where gid='"+tgid+"'";
      if(das.exeSql(ssql)) return true; //存款成功
    	 else return false; // 存款失败
    }
   
  protected void finalize()// 析构函数
	{
		das.finalize();
	}   
   
}
