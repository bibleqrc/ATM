
package atm;
public class moneyMachine 
{
   public String printBill(float money,String gid,float leftmoney)//打印取款清单和余额查询
   {
			String msg;
			//msg="\n";
			msg=" 操作账号: "+gid+"\n";
			msg+=" 操作类型: 取款"+"\n";
			msg+=" 操作金额: "+money+"\n";
			msg+=" 账号余额: "+leftmoney+"\n";
			return msg;
   }
}
