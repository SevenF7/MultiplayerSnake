package MultiplayerSnake.Client.view;

import javax.swing.JFrame;

import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;

public class MainMenu {

	private JFrame frame;
	private Sound sound;
	private Boolean isMute = false;
	public static Boolean isConnect = false;
	public static Boolean isWanNing = false;

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//启动bgm
		sound = new Sound("C:\\Users\\SevenF\\Desktop\\Zml\\Java\\Test\\src\\MultiplayerSnake\\Client\\music\\MainMenuBgm.wav" , isMute);
		
		//初始化frame
		frame = new JFrame();
		frame.setTitle("万宁贪吃蛇");
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 750, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		//设置JPanel
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 736, 690);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//贪吃蛇标题
		JLabel title = new JLabel("万宁贪吃蛇");
		//获得icon图片*
		Icon icon = new ImageIcon("src/MultiplayerSnake/Client/image/icon.png");
		title.setIcon(icon);
		title.setVerticalAlignment(SwingConstants.TOP);
		title.setFont(new Font("华文楷体", Font.BOLD, 43));
		title.setForeground(Color.ORANGE);
		title.setBounds(206, 167, 480, 153);
		panel.add(title);
		
		//开始游戏按钮
		JButton start = new JButton("开始游戏(*'▽'*)♪");
		//添加监听器，打开SelectMenu,改变bgm													(应添加ip是否连接的判断)
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isConnect) {
					//弹出提示窗口*
					JOptionPane.showMessageDialog(null , "请先建立连接♪" , "提示♪", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					frame.dispose();;
					sound.stop();
					SelectMenu window = new SelectMenu(isMute);
				}
				
			}
		});
		start.setBounds(270, 452, 171, 42);
		panel.add(start);
		
		//利用bottom的jlabel实现插入背景
		JLabel background = new JLabel("");
		Icon backIcon = new ImageIcon("src/MultiplayerSnake/Client/image/background1.png");
		background.setIcon(backIcon);
		background.setVerticalAlignment(SwingConstants.BOTTOM);
		background.setBounds(0, 0, 736, 690);
		panel.add(background);
		
		//菜单栏
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		//连接网络
		JMenu connecting = new JMenu("连接网络");
		menuBar.add(connecting);
		
		//设置IP按键
		JMenuItem setIP = new JMenuItem("设置IP");
		setIP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectionMenu window = ConnectionMenu.getInstance();
				window.setVisiable(true);
			}
		});
		connecting.add(setIP);
		
		//游戏说明
		JMenu instruction = new JMenu("游戏说明");
		menuBar.add(instruction);
		
		JMenuItem openInstruction = new JMenuItem("打开说明");
		openInstruction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					 
						if ((new File("C:\\Users\\SevenF\\Desktop\\Zml\\Java\\Test\\src\\MultiplayerSnake\\Client\\word\\instruction.docx")).exists()) {
				 
							Process p = Runtime
							   .getRuntime()
							   .exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\SevenF\\Desktop\\Zml\\Java\\Test\\src\\MultiplayerSnake\\Client\\word\\instruction.docx");
							p.waitFor();
								
						} else {
				 
							System.out.println("File is not exists");
				 
						}
				 
						System.out.println("Done");
				 
				  	  } catch (Exception ex) {
						ex.printStackTrace();
					  }
			}
		});
		instruction.add(openInstruction);
		
		//设置
		JMenu option = new JMenu("设置");
		menuBar.add(option);
		
		//设置中的静音按钮
		JRadioButtonMenuItem mute = new JRadioButtonMenuItem("静音");
		//添加静音按钮对isMute的改变
		mute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeIsMute();
				sound.Mute(isMute);
				sound = new Sound("C:\\Users\\SevenF\\Desktop\\Zml\\Java\\Test\\src\\MultiplayerSnake\\Client\\music\\MainMenuBgm.wav" , isMute);
			}
		});
		option.add(mute);
		
		//添加设置万宁模式	
		JRadioButtonMenuItem WanNing = new JRadioButtonMenuItem("万宁模式");
		WanNing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.isWanNing = !MainMenu.isWanNing;
			}
		});
		WanNing.setSelected(false);
		option.add(WanNing);
		
		frame.setVisible(true);
	
	}
	
	public Boolean getIsMute() {
		return this.isMute;
	}
	
	public void changeIsMute() {
		this.isMute = !isMute;
	}
}

