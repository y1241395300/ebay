package 网络聊天室;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class IDzhuce extends JFrame {
	JPasswordField jt2, jt3;
	JTextField jt4;

	IDzhuce() {

		ImageIcon ig1 = new ImageIcon("image\\denglu.png");
		JLabel tp = new JLabel(ig1);
		tp.setBounds(0, 0, 800, 600);
		JPanel jp = new JPanel();
		JPanel bg = new JPanel();
		add(bg);
		bg.setBounds(0, 0, 800, 600);
		bg.setLayout(null);
		jp.setOpaque(false);
		jp.setLayout(null);
		jp.setBounds(0, 0, 800, 600);
		JLabel jla = new JLabel("注册界面", JLabel.CENTER);
		jla.setForeground(Color.white);
		jla.setBounds(350, 0, 100, 200);
		JLabel jl1 = new JLabel("*QQ号", JLabel.CENTER);
		jl1.setBounds(100, 150, 100, 50);
		jl1.setForeground(Color.white);
		final JTextField jt1 = new JTextField();
		jt1.setBounds(210, 150, 200, 40);
		final JLabel jl2 = new JLabel("6-18个字符,可用字母、数字、下划线、以字母开头", JLabel.CENTER);
		jl2.setForeground(Color.RED);
		jl2.setBounds(410, 150, 300, 40);
		JLabel jl3 = new JLabel("*密码", JLabel.CENTER);
		jl3.setForeground(Color.white);
		jl3.setBounds(100, 220, 100, 50);
		jt2 = new JPasswordField(18);
		jt2.setBounds(210, 220, 200, 40);
		JLabel jl4 = new JLabel("6-18个字符", JLabel.CENTER);
		jl4.setForeground(Color.RED);
		jl4.setBounds(410, 220, 300, 40);
		JLabel jl5 = new JLabel("*确认密码", JLabel.CENTER);
		jl5.setForeground(Color.white);
		jl5.setBounds(100, 290, 100, 50);
		jt3 = new JPasswordField(18);
		jt3.setBounds(210, 290, 200, 40);
		JLabel jl6 = new JLabel("请与密码一致", JLabel.CENTER);
		jl6.setForeground(Color.RED);
		jl6.setBounds(410, 290, 300, 40);
		JLabel jl7 = new JLabel("*性别", JLabel.CENTER);
		jl7.setForeground(Color.white);
		jl7.setBounds(100, 430, 100, 50);
		final JRadioButton jr1 = new JRadioButton("男");
		jr1.setBounds(210, 430, 100, 50);
		JRadioButton jr2 = new JRadioButton("女");
		jr2.setBounds(410, 430, 100, 50);
		final ButtonGroup gr = new ButtonGroup();
		gr.add(jr1);
		gr.add(jr2);
		JLabel jl9 = new JLabel("*真实姓名", JLabel.CENTER);
		jl9.setForeground(Color.white);
		jl9.setBounds(100, 360, 100, 50);
		jt4 = new JTextField();
		jt4.setBounds(210, 360, 200, 40);
		JLabel jl10 = new JLabel("(非常重要)", JLabel.CENTER);
		jl10.setForeground(Color.RED);
		jl10.setBounds(410, 360, 300, 40);
		JButton jb1 = new JButton("确定");
		jb1.setBounds(200, 495, 200, 50);
		JButton jb2 = new JButton("取消");
		jb2.setBounds(420, 495, 200, 50);
		bg.add(jp);// 先加Jpanel
		bg.add(tp);// 再加背景图片 否则JLabel 不显示 按钮划过才显示
		jp.add(jla);
		jp.add(jl1);
		jp.add(jt1);
		jp.add(jl2);
		jp.add(jl3);
		jp.add(jt2);
		jp.add(jt3);
		jp.add(jl4);
		jp.add(jl5);
		jp.add(jl6);
		jp.add(jl7);
		jp.add(jl9);
		jp.add(jt4);
		jp.add(jl10);
		jp.add(jr1);
		jp.add(jr2);
		jp.add(jb1);
		jp.add(jb2);
		jp.add(jla);
		jp.add(jla);
		setLayout(null);

		Dimension dime = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((int) (dime.getWidth() - 800) / 2, (int) (dime.getHeight() - 600) / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				System.exit(0);
			}
		});
		jb1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = null;
				String sex = null;
				String a1 = null;
				a1 = jt1.getText();
				name = jt4.getText();
				char[] a2 = jt2.getPassword();
				char[] a3 = jt3.getPassword();
				if (jr1.isSelected()) {
					sex = "男";
				} else {
					sex = "女";
				}
				String a21 = new String(a2);
				String a31 = new String(a3);
				String zzbds = "^[A-Za-z][A-Za-z1-9_-]+$";
				String paszbds = "\\w{6,16}";
				Pattern pattern = Pattern.compile(zzbds);
				Pattern pattern4 = Pattern.compile(paszbds);
				Matcher matcher = pattern.matcher(a1);
				Matcher matcher4 = pattern4.matcher(a21);
				if (a21.equals(a31) == false)

				{
					JOptionPane.showMessageDialog(null, "两次密码不一致！");
				}

				if (matcher4.matches() == false) {
					JOptionPane.showMessageDialog(null, "密码格式不正确");
				}
				if (a21.equals(a31) == true && matcher4.matches() == true) {

					try {
						jdbcbean.getconnection();
						String sql = "INSERT INTO test.users()VALUES('" + a1 + "','" + a21 + "','" + name + "','" + sex
								+ "',0)";
						System.out.println(sql);
						int rs = jdbcbean.updata(sql);
						if (rs > 0) {
							JOptionPane.showMessageDialog(null, "注册成功");
							jdbcbean.clean();
							try {
								new denglu();
							} catch (Exception e1) {
								// TODO 自动生成的 catch 块
								e1.printStackTrace();
							}
							setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "用户名已注册");
						}
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}

				}

				if (matcher.matches() == false) {
					JOptionPane.showMessageDialog(null, "QQ号格式非法！");

				}

			}
		});

	}

}
