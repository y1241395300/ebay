package 网络聊天室;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.*;

class denglu extends JFrame {
	JLabel jl1, jl2, jl3, jl4;
	JTextField jt1, jt3;
	JPasswordField jt2;
	JLabel jl5;
	JPanel jp1, jp2, jp;
	JButton jb1, jb2, jb3;
	String s, name, names, IDs;
	int ans;
	ImageIcon ig1;
	InputStream in;

	public static void main(String[] args) {
		new denglu();
	}

	denglu() {
		try {
			in = new FileInputStream("user.properties");
		} catch (FileNotFoundException e3) {
			// TODO 自动生成的 catch 块
			e3.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(in);
			names = prop.getProperty("ID");
			IDs = prop.getProperty("password");
			// in.close();
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			throw new RuntimeException("读取数据库配置文件异常！", e1);
		}
		ig1 = new ImageIcon("image\\denglu.png");
		jp1 = new JPanel();

		jp2 = new JPanel();
		add(jp2);
		add(jp2);
		jl5 = new JLabel(ig1);
		jl5.setBounds(0, 0, 800, 600);
		jp2.setLayout(null);
		jp1.setOpaque(false);
		jp1.setLayout(null);
		jp1.setBounds(0, 0, 800, 600);
		jl1 = new JLabel("QQ号");
		jl1.setForeground(Color.white);
		jl1.setBounds(200, 70, 100, 80);
		jl2 = new JLabel("密码");
		jl2.setForeground(Color.white);
		jl2.setBounds(200, 185, 100, 80);
		jt1 = new JTextField();
		jt1.setBounds(250, 80, 300, 50);
		jt2 = new JPasswordField(18);
		jt2.setBounds(250, 200, 300, 50);
		jb1 = new JButton("确定");
		jb1.setBounds(220, 300, 100, 30);
		jp2.repaint();
		jb2 = new JButton("重置");
		jb2.setBounds(340, 300, 100, 30);
		jb3 = new JButton("注册");
		jb3.setBounds(460, 300, 100, 30);
		jp2.setBounds(0, 0, 800, 600);
		jp2.add(jp1);
		jp2.add(jl5);
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jt1);
		jp1.add(jt2);
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		jt1.setText(names);
		jt2.setText(IDs);
		setTitle("QQ登陆");
		Dimension dime = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) (dime.getWidth() - 800) / 2, (int) (dime.getHeight() - 600) / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String a1 = jt1.getText();
				char[] a2 = jt2.getPassword();
				String a21 = new String(a2);
				String a3 = ("");
				name = jt1.getText();
				jdbcbean.getconnection();
				ResultSet result, result2;
				try {
					InetAddress addr = InetAddress.getLocalHost();
					jdbcbean.updata("INSERT INTO test.pass()VALUES('/" + addr.getHostAddress() + "','" + name + "')");
					result = jdbcbean.cha("select * from test.users");
					result.last();
					int n = result.getRow();
					result.beforeFirst();
					ArrayList<String> user = new ArrayList<String>();
					ArrayList<String> password = new ArrayList<String>();
					while (result.next()) {
						user.add(result.getString("ID"));
						password.add((result.getInt("password") + ""));
					}
					String user1[] = new String[user.size()];
					String password1[] = new String[password.size()];
					for (int i = 0; i < user.size(); i++) {
						user1[i] = user.get(i);
						password1[i] = password.get(i);
					}

					if (password1[ans].equals(a21)) {
						int index = JOptionPane.showConfirmDialog(null, "要保存密码么？", "提示",
								JOptionPane.YES_NO_CANCEL_OPTION);
						if (index == 0) {
							FileOutputStream out = new FileOutputStream("user.properties");
							Properties prop = new Properties();
							try {
								prop.load(in);
								prop.setProperty("ID", a1);
								prop.setProperty("password", a21);
								prop.store(out, "");
							} catch (IOException e1) {
								// TODO 自动生成的 catch 块
								throw new RuntimeException("读取数据库配置文件异常1！", e1);
							} finally {
								try {
									out.close();
									in.close();
								} catch (IOException e1) {
									// TODO 自动生成的 catch 块
									e1.printStackTrace();
								}
							}

						}
						if (index == 1) {
							FileOutputStream out = new FileOutputStream("user.properties");
							Properties prop = new Properties();
							try {
								prop.load(in);
								prop.setProperty("ID", "");
								prop.setProperty("password", "");
								prop.store(out, "");
							} catch (IOException e1) {
								// TODO 自动生成的 catch 块
								throw new RuntimeException("读取数据库配置文件异常2！", e1);
							} finally {
								try {
									out.close();
									in.close();
								} catch (IOException e1) {
									// TODO 自动生成的 catch 块
									e1.printStackTrace();
								}
							}
						}
						result2 = jdbcbean.cha("SELECT online from test.users where ID='" + a1 + "'");
						result2.last();
						int n2 = result2.getRow();
						result2.beforeFirst();
						ArrayList<String> online = new ArrayList<String>();
						while (result2.next()) {
							for (int i = 0; i < n2; i++) {
								online.add((result2.getInt("online") + ""));
							}
						}
						String online1[] = new String[online.size()];
						for (int i = 0; i < online.size(); i++) {
							online1[i] = online.get(i);
						}
						if (online1[0].equals("0") == true) {
							new zhujiemian(name);
							jdbcbean.updata("UPDATE test.users SET online=1 where ID='" + a1 + "'");
							jdbcbean.clean();
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "账号已登陆");
						}
					}

					else {
						JOptionPane.showMessageDialog(null, "账号或密码错误");

					}

				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (FileNotFoundException e2) {
					// TODO 自动生成的 catch 块
					e2.printStackTrace();
				} catch (UnknownHostException e2) {
					// TODO 自动生成的 catch 块
					e2.printStackTrace();
				}

			}
		});
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				jt1.setText("");
				jt2.setText("");
			}
		});
		jb3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				new IDzhuce();
				setVisible(false);
			}
		});

	}
}
