//各种窗体和按钮事件
package atm;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.awt.Font;

public class atmScreen implements ActionListener {
	Frame fram;

	JPanel welcome, login_in, operation, getmoney, query, changepwd, transfer,   //容器定义
			PnlMsg,cunk;

	JButton btnwel, confirm, RMB_get, RMB_query, pwd_change, operation_quit,    //按钮定义
			RMB_transfer, expand, quit, b1, b2, b3, b4, b5, b6, back, dok,
			back1, quit1, cf1,cunkuan,do_cun;

	JButton login_off, y1, b7, b8, c1, c2, y2, d1, d2, login;                   

	JLabel msg3, lbleft, msgstr;

	Label msghint;

	TextField ta, oldpwd, newpwd1, newpwd2, tran_account, tran_RMB,         //输入框
			tran_passwd,cun_money;

	JTextArea lblMsg;

	String msg;

	CardLayout clt = new CardLayout();// 系统界面布局方式，网格布局

	float gmoney = 0;

	int nstate = 0, tran_state = 0;// 判别多个状态 1、修改密码；2、数据输入；3、菜单

	String gid, pwd;

	Account act = new Account();

	moneyMachine mce = new moneyMachine();

	readCard rcd = new readCard();

	dataAccess dac = new dataAccess();

	public void mainFram() {
		fram = new Frame("银行管理系统");
		quit = new JButton("退出");
		back = new JButton("返回");
		back.addActionListener(this);
		quit.addActionListener(this);

		this.showRMB_transfer();
		this.showChangePwd();
		this.showGetMoney();
		this.showHint();
		this.showJMenu();
		this.showQueryLeft();
		this.showWelcome();
		this.showInput();
		this.showCunKuan();

		fram.setLayout(clt);
		fram.add(welcome, "wel");
		fram.add(login_in, "input");
		fram.add(operation, "JMenu");
		fram.add(changepwd, "Chgpwd");
		fram.add(transfer, "trans");
		fram.add(getmoney, "Getmoney");
		fram.add(query, "Queryleft");
		fram.add(PnlMsg, "Msg");
		fram.add(cunk,"cun");

		fram.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		fram.setSize(400, 300);
		fram.setLocation(400, 200);
		fram.setVisible(true);
		fram.setResizable(false);
	}

	public void showWelcome() {
		welcome = new JPanel();
		// GridLayout gdo1 = new GridLayout(3, 3, 10, 10);
		welcome.setLayout(null);
		JLabel xlbl1 = new JLabel("欢迎进入银行管理系统");
		xlbl1.setHorizontalAlignment(JLabel.CENTER);
		login = new JButton("进入系统");
		login_off = new JButton("退出系统");
		login.addActionListener(this);
		login_off.addActionListener(this);
		xlbl1.setBounds(40, 50, 300, 20);
		login.setBounds(115, 85, 150, 25);
		login_off.setBounds(115, 115, 150, 25);
		welcome.add(xlbl1);
		welcome.add(login);
		welcome.add(login_off);
	}

	public void showInput() {
		login_in = new JPanel();
		msgstr = new JLabel("请输入你的卡号");
		msghint = new Label("");
		ta = new TextField();
		confirm = new JButton("确定");
		confirm.addActionListener(this);
		y1 = new JButton("退出");
		y1.addActionListener(this);
		y2 = new JButton("返回");
		y2.addActionListener(this);
		y2.setVisible(false);
		// JPanel spnl1 = new JPanel();
		login_in.setLayout(null);

		msgstr.setBounds(50, 50, 200, 20);
		ta.setBounds(50, 70, 200, 20);
		msghint.setBounds(50, 90, 200, 20);
		confirm.setBounds(50, 110, 100, 25);
		y1.setBounds(150, 110, 100, 25);
		y2.setBounds(250, 110, 100, 25);

		login_in.add(msgstr);
		login_in.add(ta);
		login_in.add(msghint);
		login_in.add(confirm);
		login_in.add(y1);
		login_in.add(y2);
	}

	public void showJMenu() {
		operation = new JPanel();
		JLabel albl = new JLabel("请选择业务");
		// albl.setBackground(Color.blue);
		albl.setHorizontalAlignment(JLabel.CENTER);
		RMB_get = new JButton("取款");
		RMB_transfer = new JButton("转账");
		operation_quit = new JButton("退出");
		RMB_query = new JButton("查询余额");
		pwd_change = new JButton("修改密码");
		cunkuan=new JButton("存款");
		//expand = new JButton("功能拓展");
		RMB_get.addActionListener(this);
		pwd_change.addActionListener(this);
		RMB_query.addActionListener(this);
		operation_quit.addActionListener(this);
		RMB_transfer.addActionListener(this);
		cunkuan.addActionListener(this);
		operation.setLayout(null);

		albl.setBounds(100, 20, 180, 20);
		RMB_get.setBounds(65, 50, 120, 40);
		RMB_transfer.setBounds(65, 100, 120, 40);
		operation_quit.setBounds(200, 150, 120, 40);
		RMB_query.setBounds(200, 50, 120, 40);
		pwd_change.setBounds(200, 100, 120, 40);
		cunkuan.setBounds(65, 150, 120, 40);

		operation.add(albl);
		operation.add(RMB_get);
		operation.add(RMB_query);
		operation.add(pwd_change);
		operation.add(operation_quit);
		operation.add(RMB_transfer);
		operation.add(operation_quit);
		operation.add(cunkuan);
	}

	public void showGetMoney() {
		getmoney = new JPanel();
		GridLayout gdo4 = new GridLayout(4, 4);
		getmoney.setLayout(new BorderLayout());
		JLabel blbl = new JLabel("选择取款金额");
		msg3 = new JLabel("");
		b1 = new JButton("100");
		b2 = new JButton("300");
		b3 = new JButton("500");
		b4 = new JButton("800");
		b5 = new JButton("1000");
		b6 = new JButton("其他");
		b1.addActionListener(this);
		b5.addActionListener(this);
		b2.addActionListener(this);
		b6.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b7 = new JButton("返回");
		b7.addActionListener(this);
		b8 = new JButton("退出");
		b8.addActionListener(this);
		JPanel bpnl1 = new JPanel();
		bpnl1.add(blbl);
		JPanel bpnl3 = new JPanel();
		bpnl3.add(b7);
		bpnl3.add(b8);
		JPanel bpnl2 = new JPanel();
		bpnl2.setLayout(gdo4);
		bpnl2.add(b1);
		bpnl2.add(new Label());
		bpnl2.add(b4);
		bpnl2.add(b2);
		bpnl2.add(msg3);
		bpnl2.add(b5);
		bpnl2.add(b3);
		bpnl2.add(new Label());
		bpnl2.add(b6);
		bpnl2.add(new Label());
		bpnl2.add(bpnl3);
		bpnl2.add(new Label());
		getmoney.add(bpnl1, BorderLayout.NORTH);
		getmoney.add(bpnl2, BorderLayout.CENTER);
	}
	
	public void showCunKuan(){
		
			cunk = new JPanel();
			cunk.setLayout(null);
			JLabel zlzl = new JLabel("请输入存款金额");
			cun_money = new TextField(15);
			do_cun = new JButton("确定");
			do_cun.addActionListener(this);
			d1 = new JButton("返回");
			d1.addActionListener(this);
			JPanel cunl1 = new JPanel();
			JPanel cunl2 = new JPanel();
			cunl1.add(zlzl);
			cunl1.add(cun_money);
			cunl2.add(do_cun);
			cunl2.add(d1);
			
			cunl1.setBounds(40, 50, 300, 40);
			cunl2.setBounds(115, 105, 150, 35);
			cunk.add(cunl1);
			cunk.add(cunl2);
			
		}
	

	public void showQueryLeft() {
		query = new JPanel();
		// query.setLayout(new BorderLayout());
		JLabel clbl = new JLabel("账户余额");
		clbl.setHorizontalAlignment(JLabel.CENTER);
		lbleft = new JLabel("");
		JPanel cpnl1 = new JPanel();
		// JPanel cpnl3 = new JPanel();
		cpnl1.add(lbleft);
		// cpnl3.add(lbleft);
		JPanel cpnl2 = new JPanel();
		c1 = new JButton("返回");
		c1.addActionListener(this);
		c2 = new JButton("退出");
		c2.addActionListener(this);
		cpnl2.add(c1);
		cpnl2.add(c2);

		query.setLayout(null);
		clbl.setBounds(110, 20, 150, 20);
		cpnl1.setBounds(90, 40, 200, 20);
		cpnl2.setBounds(120, 70, 150, 40);

		query.add(clbl);
		query.add(cpnl1);
		query.add(cpnl2);
	}

	public void showChangePwd() {
		changepwd = new JPanel();
		GridLayout gdo6 = new GridLayout(5, 1);
		changepwd.setLayout(gdo6);
		JLabel dlbl1 = new JLabel("修改密码");
		dlbl1.setHorizontalAlignment(JLabel.CENTER);
		JLabel dlbl2 = new JLabel("请输入原密码");
		JLabel dlbl3 = new JLabel("请输入新密码");
		JLabel dlbl4 = new JLabel("请确认新密码");
		dok = new JButton("确定");
		dok.addActionListener(this);
		oldpwd = new TextField(15);
		newpwd1 = new TextField(15);
		newpwd2 = new TextField(15);
		oldpwd.setEchoChar('*');
		newpwd1.setEchoChar('*');
		newpwd2.setEchoChar('*');
		d1 = new JButton("返回");
		d1.addActionListener(this);
		d2 = new JButton("退出");
		d2.addActionListener(this);
		JPanel dpnl1 = new JPanel();
		JPanel dpnl2 = new JPanel();
		JPanel dpnl3 = new JPanel();
		JPanel dpnl4 = new JPanel();
		JPanel dpnl5 = new JPanel();
		dpnl1.add(dlbl1);
		dpnl2.add(dlbl2);
		dpnl2.add(oldpwd);
		dpnl3.add(dlbl3);
		dpnl3.add(newpwd1);
		dpnl4.add(dlbl4);
		dpnl4.add(newpwd2);
		dpnl5.add(dok);
		dpnl5.add(d1);
		dpnl5.add(d2);

		changepwd.add(dpnl1);
		changepwd.add(dpnl2);
		changepwd.add(dpnl3);
		changepwd.add(dpnl4);
		changepwd.add(dpnl5);
	}

	public void showRMB_transfer() {
		transfer = new JPanel();
		GridLayout gdo6 = new GridLayout(5, 1);
		transfer.setLayout(gdo6);
		JLabel tran = new JLabel("转账操作");
		tran.setHorizontalAlignment(JLabel.CENTER);
		JLabel tran1 = new JLabel("请输入转入账户");
		JLabel tran2 = new JLabel("请输入转账金额");
		// JLabel tran3 = new JLabel("�������������룺");
		cf1 = new JButton("确定");
		cf1.addActionListener(this);
		tran_account = new TextField(15);
		tran_RMB = new TextField(15);
		// tran_passwd = new TextField(15);
		back1 = new JButton("返回");
		back1.addActionListener(this);
		quit1 = new JButton("退出");
		quit1.addActionListener(this);
		JPanel trana = new JPanel();
		JPanel tranb = new JPanel();
		JPanel tranc = new JPanel();
		// JPanel trand = new JPanel();
		JPanel trand = new JPanel();
		trana.add(tran);
		tranb.add(tran1);
		tranb.add(tran_account);
		tranc.add(tran2);
		tranc.add(tran_RMB);
		trand.add(cf1);
		trand.add(back1);
		trand.add(quit1);

		transfer.add(trana);
		transfer.add(tranb);
		transfer.add(tranc);
		// transfer.add(trand);
		transfer.add(trand);
	}

	public void showHint() {
		PnlMsg = new JPanel();
		// PnlMsg.setLayout(new BorderLayout());
		JLabel elbl = new JLabel("操作信息提示");
		elbl.setHorizontalAlignment(JLabel.CENTER);// (Label.CENTER);
		lblMsg = new JTextArea(18, 20);
		lblMsg.setBackground(Color.LIGHT_GRAY);
		lblMsg.setFont(new Font("宋体", Font.BOLD, 18));
		// lblMsg.setAlignmentX(150);
		// MultilineLabel h = new MultilineLabel();
		JPanel epnl = new JPanel();
		epnl.add(lblMsg);
		JPanel epnl2 = new JPanel();
		epnl2.add(back);
		epnl2.add(quit);
		PnlMsg.setLayout(null);

		elbl.setBounds(120, 20, 150, 20);
		epnl.setBounds(90, 50, 200, 100);
		epnl2.setBounds(120, 180, 150, 40);

		PnlMsg.add(elbl);
		PnlMsg.add(epnl);
		PnlMsg.add(epnl2);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login)//进入输入界面
		{
			clt.show(fram, "input");
			ta.requestFocus();
		}
		if (e.getSource() == confirm)// 包含用户ID，密码和取款金额的验证等操作
		{
			if ((msgstr.getText().indexOf("卡号")) > 0)//  用户ID验证
			{
				if (!act.checkAccount(rcd.getCardID(ta.getText()))) {
					msghint.setText("您输入的账号有误，请重新输入!");
					ta.setText("");
					ta.requestFocus();
					dac.sleep(2);
					msghint.setText("");
				} else {//输入密码并获取
					ta.requestFocus();
					ta.setEchoChar('*');
					msgstr.setText("请输入密码:");
					this.gid = ta.getText();
					ta.setText("");
				}
				return;
			}

			if ((msgstr.getText().indexOf("密码")) > 0)// 密码验证操作
			{
				if (!act.checkPwd(gid, ta.getText())) {
					msghint.setText("您输入的密码有误，请重新输入!");
					ta.setText("");
					ta.requestFocus();
					dac.sleep(2);
					msghint.setText("");
				} else {
					ta.setEchoChar('\0');
					// ta = new TextField();
					ta.requestFocus();
					// ta.setEchoChar('*');
					clt.show(fram, "JMenu");
					this.pwd = ta.getText();
				}
				return;
			}

			if ((msgstr.getText().indexOf("金额")) > 0)// 手动输入金额操作
			{
				try {
					gmoney = Float.parseFloat(ta.getText());
					if (act.reduceMoney(gid, gmoney)) {
						lblMsg.setText(mce.printBill(gmoney, gid, act
								.queryBalance(gid)));
						nstate = 3;
					} else {
						lblMsg.setText("您的账户余额不足!");
						nstate = 3;
					}

				} catch (NumberFormatException e2)// 输入非法的金额
				{
					lblMsg.setText("您输入的金额错误!");
					nstate = 2;
				} finally {
					clt.show(fram, "Msg");
					return;
				}

			}
		}

		if (e.getSource() == operation_quit || e.getSource() == b8
				|| e.getSource() == c2 || e.getSource() == d2
				|| e.getSource() == login_off || e.getSource() == y1
				|| e.getSource() == quit || e.getSource() == quit1)// 退出系统
		{
			this.gid = "";
			this.pwd = "";
			act.finalize();
			dac.finalize();
			rcd.exitCard();
		}

		if (e.getSource() == RMB_get)// 进入取款界面
		{
			clt.show(fram, "Getmoney");
			return;
		}
 
		if (e.getSource() == cunkuan)// 进入取款界面
		{
			clt.show(fram, "cun");
			return;
		}
		
		if (e.getSource() == RMB_transfer)// 进入转账界面
		{
			clt.show(fram, "trans");
			return;
		}

		if (e.getSource() == RMB_query)// 进入余额查看界面
		{
			float mleft = act.queryBalance(gid);
			lbleft.setText("您的账号余额为: RMB " + mleft);
			clt.show(fram, "Queryleft");
			return;
		}

		if (e.getSource() == pwd_change)// 进入密码修改界面
		{
			clt.show(fram, "Chgpwd");
			return;
		}

		if (e.getSource() == b1)// ִ  执行取款100操作
		{
			if (!act.reduceMoney(gid, 100))
				lblMsg.setText("您的账户余额不足!");
			else {
				lblMsg.setText(mce.printBill(100, gid, act.queryBalance(gid)));

			}
			nstate = 3;
			clt.show(fram, "Msg");
			return;

		}

		if (e.getSource() == b2)// ִ 执行取款300操作
		{
			if (!act.reduceMoney(gid, 300))
				lblMsg.setText("您的账户余额不足！");
			else {
				lblMsg.setText(mce.printBill(300, gid, act.queryBalance(gid)));

			}
			nstate = 3;
			clt.show(fram, "Msg");
			return;
		}

		if (e.getSource() == b3)// 执行取款500操作
		{
			if (!act.reduceMoney(gid, 500))
				lblMsg.setText("您的账户余额不足!");
			else {
				lblMsg.setText(mce.printBill(500, gid, act.queryBalance(gid)));

			}
			nstate = 3;
			clt.show(fram, "Msg");
			return;
		}

		if (e.getSource() == b4)//  执行取款800操作
		{
			if (!act.reduceMoney(gid, 800))
				lblMsg.setText("你的账户余额不足!");
			else {
				lblMsg.setText(mce.printBill(800, gid, act.queryBalance(gid)));

			}
			nstate = 3;
			clt.show(fram, "Msg");
			return;
		}

		if (e.getSource() == b5)//  执行取款1000操作
		{
			if (!act.reduceMoney(gid, 1000))
				lblMsg.setText("您的账户余额不足!");
			else {
				lblMsg.setText(mce.printBill(1000, gid, act.queryBalance(gid)));
			}
			nstate = 3;
			clt.show(fram, "Msg");
			return;
		}

		if (e.getSource() == b6)// 进入手动输入金额取款界面
		{
			y2.setVisible(true);
			msgstr.setText("请输入金额");
			ta.setText("");
			ta.requestFocus();
			msghint.setText("");
			clt.show(fram, "input");
			return;
		}

		if(e.getSource() == do_cun)         //存款操作
		{
			String msg;
				float tr = act.transfer(gid, gid, cun_money
						.getText());
				if (tr == -1) {
					lblMsg.setText("输入信息不能为空!");
					//tran_state = 1;
					nstate = 4;
				} else if (tr == -2) {
					lblMsg.setText("系统里没有该账户信息！");
					//tran_state = 1;
					nstate = 4;
				} else {
					act.addMoney(gid, Float.parseFloat(cun_money.getText()));
					msg = " 操作账号: " + gid + "\n";
					msg += " 操作类型: 存款" + "\n";
					msg += " 操作金额: " + Float.parseFloat(cun_money.getText()) + "\n";
					msg += " 账号余额: " + act.queryBalance(gid) + "\n";
					lblMsg.setText(msg);
					nstate = 5;
				}
				clt.show(fram, "Msg");
				return;

		}
		
		if (e.getSource() == dok)// 执行修改密码操作
		{
			int t = act.changePwd(gid, pwd, oldpwd.getText(),
					newpwd1.getText(), newpwd2.getText());
			if (t == -1) {
				lblMsg.setText("原密码输入错误!");
				nstate = 1;
			} else if (t == -2) {
				lblMsg.setText("两次密码输入不同ͬ!");
				nstate = 1;
			} else if (t == -3) {
				lblMsg.setText("新密码不能为空!");
				nstate = 1;
			}
			if (t == 1) {
				lblMsg.setText("密码修改成功!");
				nstate = 3;
				this.pwd = newpwd1.getText();
				oldpwd.setText("");
				newpwd1.setText("");
				newpwd2.setText("");
			}
			clt.show(fram, "Msg");
			return;

		}

		if (e.getSource() == cf1)//  执行转账操作
		{
			String msg;
			float tr = act.transfer(gid, tran_account.getText(), tran_RMB
					.getText());
			if (tr == -1) {
				lblMsg.setText("输入信息不能为空!");
				//tran_state = 1;
				nstate = 4;
			} else if (tr == -2) {
				lblMsg.setText("系统里没有该账户信息！");
				//tran_state = 1;
				nstate = 4;
			} else if (tr == -3) {
				lblMsg.setText("您的账户余额不足！");
				//tran_state = 1;
				nstate = 4;
			} else {
				msg = " 转入账号: " + tran_account.getText() + "\n";
				msg += " 操作类型: 转账" + "\n";
				msg += " 操作金额: " + Float.parseFloat(tran_RMB.getText()) + "\n";
				msg += " 账号余额: " + act.queryBalance(gid) + "\n";
				act.addMoney(tran_account.getText(), Float.parseFloat(tran_RMB.getText()));
				act.reduceMoney(gid, Float.parseFloat(tran_RMB.getText()));
				lblMsg.setText(msg);
				//tran_state = 3;
				nstate = 5;
			}
			clt.show(fram, "Msg");
			return;

		}
		
		//判断返回状态̬
		if (e.getSource() == back) { 
			if (nstate == 1)
				clt.show(fram, "Chgpwd");
			else if (nstate == 2)
				clt.show(fram, "input");
			else if (nstate == 3)
				clt.show(fram, "JMenu");
			else if(nstate == 4)
				clt.show(fram, "trans");
			else if(nstate == 5)
				clt.show(fram, "JMenu");
		}
		//返回界面到业务选择
		if (e.getSource() == b7 || e.getSource() == c1 || e.getSource() == d1
				|| e.getSource() == back1)
			clt.show(fram, "JMenu");

		if (e.getSource() == y2)
			clt.show(fram, "Getmoney");
	}
}