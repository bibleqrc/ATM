//主方法负责调用其他类
package atm;

public class Atm 
{	
	atmScreen atms=new atmScreen();//ʵ
	
	
	public void go()	
	{
		atms.mainFram();
		
	}
 
	
	public static void main(String[] args) //实例化ATM软件界面
	{
		Atm atm=new Atm();
		atm.go();
	}	

}
