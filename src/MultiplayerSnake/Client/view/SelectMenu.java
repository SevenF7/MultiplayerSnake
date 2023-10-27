package MultiplayerSnake.Client.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class SelectMenu {

	private JFrame frame;
	private Sound sound;
	private Boolean isMute;
	private String choose = "";
	public static int code;

	/**
	 * Create the application.
	 */
	public SelectMenu(Boolean isMute) {
		this.isMute = isMute;
		initialize();
	}
	
	public Boolean getIsMute() {
		return this.isMute;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//加载bgm
		sound = new Sound("C:\\Users\\SevenF\\Desktop\\Zml\\Java\\Test\\src\\MultiplayerSnake\\Client\\music\\SelectMenuBgm.wav" , isMute);
		
		//初始化frame
		frame = new JFrame();
		frame.setTitle("请选择你的英雄");
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 750, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		//设置JPanel
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 736, 713);
		frame.getContentPane().add(panel);
		
		//创建字符串数组，用于保存角色描述，精简box代码
		String[] discriptions = new String[6];
		discriptions[0] = "千劫(爆炸蛇)";
		discriptions[1] = "华(无敌蛇)";
		discriptions[2] = "梅比乌斯(伸缩蛇)";
		discriptions[3] = "维尔薇(翻转蛇)";
		discriptions[4] = "帕朵·菲利斯(加速蛇)";
		discriptions[5] = "阿波尼亚(干扰蛇)";
		
		JRadioButton[] jrb = new JRadioButton[6];
		for(int i = 0 ; i < 6 ; i ++) {
			jrb[i] = new JRadioButton(String.valueOf(i + 1));
			jrb[i].addActionListener(new ListenerRadioButton());
		}
		ButtonGroup bg = new ButtonGroup();
		Box vBoxLeft = Box.createVerticalBox();
		Box vBoxRight = Box.createVerticalBox();
		
		//创建选择每一个角色的垂直box
		for(int i = 1 ; i <= 6 ; i ++) {
			String snake = "Snake" + i + ".png";
			JLabel jlabel1 = new JLabel();									//图片
			//获取icon图片*
			Icon Snake = new ImageIcon("src/MultiplayerSnake/Client/image/" + snake);
			jlabel1.setIcon(Snake);
			JLabel jlabel2 = new JLabel(discriptions[i - 1]);				//描述
			jlabel2.setFont(new Font("华文仿宋", Font.BOLD, 20));
			bg.add(jrb[i - 1]);
			Box vBox = Box.createVerticalBox();
			vBox.add(jlabel1);
			vBox.add(jlabel2);
			vBox.add(jrb[i - 1]);
			if(i <= 3) {
				vBoxLeft.add(vBox);
				vBoxLeft.add(Box.createVerticalGlue());
			}
				
			else {
				vBoxRight.add(vBox);
				vBoxRight.add(Box.createVerticalGlue());
			}
				
		}
		
		//水平box居中处理*
		Box hBox = Box.createHorizontalBox();
		hBox.add(Box.createHorizontalGlue());
		hBox.add(vBoxLeft);
		hBox.add(Box.createHorizontalGlue());
		hBox.add(vBoxRight);
		hBox.add(Box.createHorizontalGlue());
		
		Box finalBox = Box.createVerticalBox();
		finalBox.setBounds(0, 0, 736, 713);
		JButton select = new JButton("就是你啦♪");
		select.setBackground(Color.yellow);
		//添加select按钮的监听器，如果buttongroup中未被选择，会跳出弹窗
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(choose.equals("")) {
					//弹出提示窗口*
					JOptionPane.showMessageDialog(null , "选一条小蛇再进去吧♪" , "提示♪", JOptionPane.PLAIN_MESSAGE);
				}
				else {
					//关闭SelectMenu窗口，打开GameMenu窗口，关闭当前bgm，传递code
					frame.dispose();
					sound.stop();
					code = Integer.parseInt(choose);
					//打开GameMenu
					GameMenu window = new GameMenu(isMute);
				}
			}
		});
		panel.setLayout(null);
		
		//select按钮居中处理*
		select.setAlignmentX(Component.CENTER_ALIGNMENT);
		finalBox.add(hBox);
		finalBox.add(select);
		finalBox.add(Box.createVerticalGlue());
		
		panel.add(finalBox);
		
		//添加背景
		JLabel background = new JLabel("");
		Icon backIcon = new ImageIcon("src/MultiplayerSnake/Client/image/background2.png");
		background.setIcon(backIcon);
		background.setVerticalAlignment(SwingConstants.BOTTOM);
		background.setBounds(0, 0, 736, 713);
		panel.add(background);
		
		frame.setVisible(true);
	}
	
	class ListenerRadioButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			choose = e.getActionCommand();
		}
	}

}
