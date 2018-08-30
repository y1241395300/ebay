package 网络聊天室;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.net.SocketFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class zhujiemian extends JFrame{
	JLabel jl1,jl2,jl3,jl4;
	JTextField jt1,jt3;
	JPasswordField jt2;
	JPanel jp1;
	JButton jb1,jb2,jb3;
	 Socket s;
	 String name;
	zhujiemian(String name){
		this.name=name;
					InetAddress ia;
					try {
						ia = InetAddress.getByName("坠机堡垒");
					 s=new Socket(ia, 40000);
						room r=new room(s,name);
						 Thread a2=new Thread(r);
						 a2.start();
						 shou1 sss=new shou1(s);
						 Thread a1=new Thread(sss);
						 a1.start();	 
					} catch (UnknownHostException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					}
				
				
			
		
}
class shou1 implements Runnable{
	Socket s=null;
	shou1(Socket s){
		this.s=s;
	}
	public void run() {
		
				try {
					while(true) {
					InputStream is=s.getInputStream();
					byte b[]=new byte[1024];
					int len=is.read(b);
					room.jta1.append(new String(b,0,len));
					}
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
	
	
}
class room extends JFrame implements Runnable{
	JPanel jp1,jp2,jp3,jp;
	JScrollPane js1,js2;
	static JTextArea jta1;
	JTextArea jta2;
	JLabel jl1,jl2;
	JButton jb1,jb2,jb3,jbb1,jbb2;
	Socket s=null;
	OutputStream os=null;
	String name;
	JTable jtab;
	int j=-1;
	int gg=1;
	ArrayList<String> history;
	room(Socket s,String name){
		this.s=s;
		this.name=name;
	}
	public void run(){
		final JPanel jpp=new JPanel();
		final JLabel jlab=new JLabel("聊天记录",JLabel.CENTER);
		jbb1=new JButton("上一页");
		jbb2=new JButton("下一页");
		jp=new JPanel();
		jp.setLayout(null);
		jp1=new JPanel();
		jp1.setBounds(0, 0, 600, 20);
		jp2=new JPanel();
		jp2.setBounds(0, 20, 600, 300);
		jp3=new JPanel();
		jp3.setBounds(0, 320, 600,280);
		jb3=new JButton("历史记录");
		jb3.setBounds(200, 0,150, 20);
		jl2=new JLabel();
		jl2.setText("昵称:"+name);
		jl2.setBounds(0, 205, 300, 30);
		jta1=new JTextArea();
		jta1.setBackground(Color.GRAY);
		jta1.setForeground(Color.RED);
		js1=new JScrollPane(jta1);
		js1.setBounds(0, 0, 600, 300);
		jta2=new JTextArea();
		js2=new JScrollPane(jta2);
		js2.setBounds(0, 0, 600, 200);
		jb1=new JButton("发送");
		jb1.setBounds(405, 205, 75, 30);
		jb2=new JButton("清屏");
		jb2.setBounds(500, 205, 75, 30);
		jp1.add(jb3);
		jp3.add(jl2);
		jp2.add(js1);
		jp3.add(js2);
		jp3.add(jb1);
		jp3.add(jb2);
		jp1.setLayout(null);
		jp2.setLayout(null);
		jp3.setLayout(null);
		add(jp);
		jp.add(jp1);
		jp.add(jp2);
		jp.add(jp3);
		setSize(600, 600);
		setLocation(600, 200);
		setVisible(true);
		jp.setVisible(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				jdbcbean.getconnection();
				try {
					jdbcbean.updata("UPDATE test.users SET online=0 WHERE ID='"+name+"'");
					System.exit(0);
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		jb3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String []tou= {"昵称","历史记录"};
				final DefaultTableModel model=new DefaultTableModel(tou,0);
				jtab=new JTable(model);
			jdbcbean.getconnection();
			try {
				ResultSet rs=jdbcbean.cha("SELECT history from test.history where ID='"+name+"'");
				rs.last();
				int num=rs.getRow();
				rs.beforeFirst();
				history=new ArrayList<String>();
		   while(rs.next()) {
				history.add(rs.getString("history"));		
		   }
			
		   String history1[] = new String[history.size()];
			for(int i=0;i<5;i++) {
				history1[i]=history.get(i);
			//	System.out.println(history1[i]);
			   String[]args= {name,history1[i]};
			   model.addRow(args);
			}
			} catch (SQLException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
				if(j%2!=0) {
			remove(jp2);
			remove(jp3);
				jp.add(jpp);
				jp.updateUI();
				jlab.setBounds(0,50,600,50);
				jtab.setBounds(0, 100, 600, 80);
				jbb1.setBounds(50, 180, 200, 50);
				jbb2.setBounds(320, 180, 200, 50);
			
				jbb1.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						gg--;
						model.setRowCount(0); 
						  String history1[] = new String[history.size()];
							if(gg>0) {
							for(int i=((gg-1)*5);i<gg*5;i++) {
								history1[i]=history.get(i);
							   String[]args= {name,history1[i]};
							   model.addRow(args);
						
							}
				
							}
							else {
								JOptionPane.showMessageDialog(null, "已经是第一页");
							}
					}
				});
				jbb2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO 自动生成的方法存根
						if(gg<(history.size()/5)) {
						model.setRowCount(0); 
						  String history1[] = new String[history.size()];
							for(int i=gg*5;i<((gg+1)*5);i++) {
								history1[i]=history.get(i);
							   String[]args= {name,history1[i]};
							   model.addRow(args);		 
							}
							gg++;
						}
						else {
							JOptionPane.showMessageDialog(null, "已经是最后一页");
						}
					}
				});
				jpp.setLayout(null);
				jpp.add(jtab);
				jpp.add(jlab);
				jpp.add(jbb1);
				jpp.add(jbb2);
				jpp.setBounds(0, 20, 600, 600);
	
				}
				if(j%2==0) {
	      				remove(jpp);
					jp.add(jp2);
					jp.add(jp3);
			
				}
				j++;
		update(getGraphics());
				try {
					jdbcbean.clean();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		jb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				jta1.setText("");
				jta2.setText("");
			}
		});
		jb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
			
				try {							
					os=s.getOutputStream();
					SimpleDateFormat sd=new SimpleDateFormat("HH:mm:ss");
					os.write((jl2.getText()+"在"+sd.format(new Date())+"说:"+jta2.getText()).getBytes());	
					try {
						jdbcbean.getconnection();
						jdbcbean.updata("insert into test.history()values('"+name+"','"+jl2.getText()+"在"+sd.format(new Date())+"说:"+jta2.getText()+"')");
						jta2.setText("");
						jdbcbean.clean();
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				} catch (UnknownHostException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		}

	}
	
