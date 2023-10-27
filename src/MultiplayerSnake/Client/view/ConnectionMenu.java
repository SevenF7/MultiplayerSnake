package MultiplayerSnake.Client.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConnectionMenu {

	private static ConnectionMenu instance = null;
	private JFrame frame;
	private JPanel panel;
	private JButton connect;
	private JTextField textField;
	public static InetAddress address;
	private Socket testSocket;
	public ObjectOutputStream out;
	public ObjectInputStream in;
	public BufferedReader bin;
	public PrintWriter pout;
	
	private ConnectionMenu() {
		initialize();
	}
	
	public static ConnectionMenu getInstance() {
		if(instance == null) {
			instance = new ConnectionMenu();
		}
			return instance;
	}
	
	public void initialize() {
		this.frame = new JFrame();
		this.panel = new JPanel();
		this.panel.setBackground(Color.gray);
		
		this.textField = new JTextField();
		textField.setPreferredSize(new Dimension(150 , 20));
		this.connect = new JButton("连接");
		connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					address = InetAddress.getByName(textField.getText());
					testSocket = new Socket(address , 7777);
					JOptionPane.showMessageDialog(null , "连接成功♪" , "提示♪", JOptionPane.PLAIN_MESSAGE);
					MainMenu.isConnect = true;
					frame.setVisible(false);
					testSocket.close();
				} catch (UnknownHostException e1) {
					//弹出提示窗口*
					JOptionPane.showMessageDialog(null , "请输入正确形式的IP地址♪" , "提示♪", JOptionPane.PLAIN_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null , "无法连接，请输入正确的IP地址♪" , "提示♪", JOptionPane.PLAIN_MESSAGE);
				}
			}
			
		});
		
		Box hBox = Box.createHorizontalBox();
		hBox.add(Box.createHorizontalGlue());
		hBox.add(connect);
		hBox.add(Box.createHorizontalGlue());
		
		Box vBox = Box.createVerticalBox();
		vBox.add(textField);
		vBox.add(hBox);
		panel.add(vBox);
		
		
		frame.add(panel);
		frame.setTitle("请输入服务器IP:");
		frame.setBounds(100, 100, 350, 150);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
	}
	
	public void setVisiable(Boolean b) {
		frame.setVisible(b);
	}

}
