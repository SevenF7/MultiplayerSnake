package MultiplayerSnake.Client.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import MultiplayerSnake.Client.obj.*;
import MultiplayerSnake.Client.skills.SkillAccelerate;
import MultiplayerSnake.Client.skills.SkillBoom;
import MultiplayerSnake.Client.skills.SkillChangeDirection;
import MultiplayerSnake.Client.skills.SkillFilp;
import MultiplayerSnake.Client.skills.SkillInvincible;
import MultiplayerSnake.Client.skills.SkillLonger;
import MultiplayerSnake.Client.utils.GameUtils;
import MultiplayerSnake.Client.utils.SendEMail;
import MultiplayerSnake.Client.utils.SnakeList;

public class GameMenu{

	//创建客户端
	private Socket socket;
	private ServerSocket serverSocket;
	private int numOfClient;
	
	//数据传输
	private BufferedReader bin;
	private PrintWriter pout;
	private int BoomX;
	private int BoomY;
	private int FoodX;
	private int FoodY;
	private String direction1;
	private String direction2;
	
	//窗体
	private MyPanelA myPanelA;
	private MyPanelB myPanelB;
	private JFrame frame;
	
	//音乐
	private Sound sound;
	public static Boolean isMute;
	
	//蛇的编号(由SelectMenu传输)
	private int code = SelectMenu.code;
	public static int code1;
	public static int code2;
	
	//游戏状态 1.游戏中 2.失败 3.胜利
	public static int state1 = 1;
	public static int state2 = 1;
	
	//定义双缓存图片
	Image offScreenImage = null;
	
	//窗口的宽高
	private int winWidth = 950;
	private int winHeight = 790;
	
	//创建蛇头对象
	public static SnakeList snakeList;
	public static List<GameUtils> imageList;
	HeadObj headObj1;
	HeadObj headObj2;
	
	//创建蛇身体的集合
	private List<BodyObj> bodyObjList1 = new ArrayList<>();
	private List<BodyObj> bodyObjList2 = new ArrayList<>();	//测试
	
	
	//创建食物
	public FoodObj foodObj = new FoodObj().getFoodObj();
	
	//创建炸弹
	public BoomObj boomObj = new BoomObj().getBoomObj();

	/**
	 * Create the application.
	 */
	public GameMenu(Boolean isMute) {
		GameMenu.isMute = isMute;
		try {
			socket = new Socket(ConnectionMenu.address , 7777);
			bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pout = new PrintWriter(socket.getOutputStream() , true);
			//获得是第几个进入的
			numOfClient = Integer.parseInt(bin.readLine());		
			bin.close();
			pout.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//加载服务器端(palyer1)
		if(numOfClient == 3) {
			try {
				serverSocket = new ServerSocket(8888);
				System.out.println("启动主机.....");
				System.out.println("Player1创建成功！");
				System.out.println("等待Player2进入.......");
				Socket socket = serverSocket.accept();
				System.out.println("已连接");
				bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pout = new PrintWriter(socket.getOutputStream() , true);
				//传输蛇的编号
				GameMenu.code1 = code;
				pout.println(GameMenu.code1);
				GameMenu.code2 = Integer.parseInt(bin.readLine());
				
				//初始化蛇的列表
				snakeList = SnakeList.getInstance();
				imageList = snakeList.getSnakeList();
				
				//加载音乐
				sound = new Sound("C:\\Users\\SevenF\\Desktop\\Zml\\Java\\Test\\src\\MultiplayerSnake\\Client\\music\\GameBgm.wav" , GameMenu.isMute);
				//加载窗体
				this.frame = new JFrame();
				frame.setTitle("万宁贪吃蛇Player1");
				frame.setSize(winWidth, winHeight);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
				
				//对蛇头进行初始化
				this.headObj1 = new HeadObj(imageList.get(code1 - 1).getRightImage() , 90 , 570 , this , code1 , 1 , true);
				this.headObj2 = new HeadObj(imageList.get(code2 - 1).getRightImage() , 90 , 480 , this , code2 , 2 , false);			//测试
				//对蛇身体进行初始化
				bodyObjList1.add(new BodyObj(imageList.get(code1 - 1).getBodyImage() , 60 , 570 , this , code1));
				bodyObjList1.add(new BodyObj(imageList.get(code1 - 1).getBodyImage() , 30 , 570 , this , code1));
				bodyObjList2.add(new BodyObj(imageList.get(code2 - 1).getBodyImage() , 60 , 480 , this , code2));
				bodyObjList2.add(new BodyObj(imageList.get(code2 - 1).getBodyImage() , 30 , 480 , this , code2));

				
				//添加键盘事件		(添加技能使用,空格键为使用技能)
				frame.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_SPACE) {
							imageList.get(code1 - 1).getSkill().runSkill();
							
						}
					}
				});
				
				frame.setVisible(true);
				
				//一直调用repaint,实现蛇的不断运动
				this.myPanelA = new MyPanelA();
				new Thread(new Runnable() {
					public void run() {
						while (true) {
							try {
									Thread.sleep(125);
							} catch (InterruptedException e) {
									e.printStackTrace();
							}
							if(GameMenu.state1 == 1 || GameMenu.state2 == 1) {
								//在游戏中才调用repaint()
								myPanelA.repaint();
							}
						}
					}
				}).start();
				
				frame.add(myPanelA);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		//加载客户端(player2)
		else {
			try {
				socket = new Socket(ConnectionMenu.address , 8888);
				bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pout = new PrintWriter(socket.getOutputStream() , true);
				//传输蛇的编号
				GameMenu.code2 = code;
				pout.println(GameMenu.code2);
				GameMenu.code1 = Integer.parseInt(bin.readLine());
				
				//初始化蛇的列表
				snakeList = SnakeList.getInstance();
				imageList = snakeList.getSnakeList();
				
				//加载音乐
				sound = new Sound("C:\\Users\\SevenF\\Desktop\\Zml\\Java\\Test\\src\\MultiplayerSnake\\Client\\music\\GameBgm.wav" , GameMenu.isMute);
				//加载窗体
				this.frame = new JFrame();
				frame.setTitle("万宁贪吃蛇Player2");
				frame.setSize(winWidth, winHeight);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setResizable(false);
				
				//对蛇头进行初始化
				this.headObj1 = new HeadObj(imageList.get(code1 - 1).getRightImage() , 90 , 570 , this , code1 , 1 , false);
				this.headObj2 = new HeadObj(imageList.get(code2 - 1).getRightImage() , 90 , 480 , this , code2 , 2 , true);			//测试
				//对蛇身体进行初始化
				bodyObjList1.add(new BodyObj(imageList.get(code1 - 1).getBodyImage() , 60 , 570 , this , code1));
				bodyObjList1.add(new BodyObj(imageList.get(code1 - 1).getBodyImage() , 30 , 570 , this , code1));
				bodyObjList2.add(new BodyObj(imageList.get(code2 - 1).getBodyImage() , 60 , 480 , this , code2));
				bodyObjList2.add(new BodyObj(imageList.get(code2 - 1).getBodyImage() , 30 , 480 , this , code2));

				
				//添加键盘事件		(添加技能使用,空格键为使用技能)
				frame.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						if(e.getKeyCode() == KeyEvent.VK_SPACE) {
							imageList.get(code2 - 1).getSkill().runSkill();	
							
						}
					}
				});
				
				frame.setVisible(true);
				
				//一直调用repaint,实现蛇的不断运动
				this.myPanelB = new MyPanelB();
				new Thread(new Runnable() {
					public void run() {
						while (true) {
							try {
									Thread.sleep(125);
							} catch (InterruptedException e) {
									e.printStackTrace();
							}
							if(GameMenu.state2 == 1 || GameMenu.state1 == 1) {
								//在游戏中才调用repaint()
								myPanelB.repaint();
							}
						}
					}
				}).start();
				
				frame.add(myPanelB);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	//绘制提示语1
	public void propmtA(Graphics g) {
		g.setColor(Color.pink);
		//失败
		if(GameMenu.state1 == 2) {
			g.fillRect(220, 240, 400, 70);
			GameUtils.drawWords(g, "你输了呦♪", Color.yellow, 40, 325, 290);
			SendEMail email = new SendEMail(GameMenu.state1) ;
			email.send();
		}
		//胜利
		else if(GameMenu.state1 == 3) {
			g.fillRect(220, 240, 400, 70);
			GameUtils.drawWords(g, "恭喜你赢啦♪", Color.yellow, 40, 325, 290);
			SendEMail email = new SendEMail(GameMenu.state1) ;
			email.send();
		}
	}
	//绘制提示语2
	public void propmtB(Graphics g) {
		g.setColor(Color.pink);
		//失败
		if(GameMenu.state2 == 2) {
			g.fillRect(220, 240, 400, 70);
			GameUtils.drawWords(g, "你输了呦♪", Color.yellow, 40, 325, 290);
			SendEMail email = new SendEMail(GameMenu.state2) ;
			email.send();
		}
		//胜利
		else if(GameMenu.state2 == 3) {
			g.fillRect(220, 240, 400, 70);
			GameUtils.drawWords(g, "恭喜你赢啦♪", Color.yellow, 40, 325, 290);
			SendEMail email = new SendEMail(GameMenu.state2) ;
			email.send();
		}
	}


	private class MyPanelA extends JPanel{
		public void paint(Graphics g) {
			super.paint(g);	
			
			//传输1的数据给2
			try {
				//设置需要传输的数据
				FoodX = foodObj.getX();
				FoodY = foodObj.getY();
				BoomX = boomObj.getX();
				BoomY = boomObj.getY();
				direction1 = headObj1.getDirection();
				
				//发送传输数据（注意传输顺序）
				pout.println(FoodX);
				pout.println(FoodY);
				pout.println(BoomX);
				pout.println(BoomY);
				//发送技能状态
				if(GameMenu.code1 == 1) {
					pout.println(SkillBoom.skillState);		
				}
				else if(GameMenu.code1 == 2) {
					pout.println(SkillInvincible.skillState);
				}
				else if(GameMenu.code1 == 3) {
					pout.println(SkillLonger.skillState);
				}
				else if(GameMenu.code1 == 4) {
					pout.println(SkillFilp.skillState);
				}
				else if(GameMenu.code1 == 5) {
					pout.println(SkillAccelerate.skillState);
				}
				else if(GameMenu.code1 == 6) {
					pout.println(SkillChangeDirection.skillState);
					//阿波尼亚技能实现
					if(SkillChangeDirection.skillState == 2) {
						switch(direction2) {
							case"up":
								direction2 = "left";
								break;
							case"left":
								direction2 = "down";
								break;
							case"down":
								direction2 = "right";
								break;
							case"right":
								direction2 = "up";
								break;
							default:
								break;
						}
						headObj2.setDirection(direction2);
						headObj2.setImage(headObj2.getImage(direction2));
						headObj2.move();	//
						pout.println(direction2);
						SkillChangeDirection.skillState = 3;
					}
				}
				pout.println(direction1);

				//接收传输数据（注意传输顺序）
				if(GameMenu.code2 == 1) {
					SkillBoom.skillState = Integer.parseInt(bin.readLine());
				}
				else if(GameMenu.code2 == 2) {
					SkillInvincible.skillState = Integer.parseInt(bin.readLine());
				}
				else if(GameMenu.code2 == 3) {
					SkillLonger.skillState = Integer.parseInt(bin.readLine());
				}
				else if(GameMenu.code2 == 4) {
					SkillFilp.skillState = Integer.parseInt(bin.readLine());
				}
				else if(GameMenu.code2 == 5) {
					SkillAccelerate.skillState = Integer.parseInt(bin.readLine());
				}
				else if(GameMenu.code2 == 6) {
					SkillChangeDirection.skillState = Integer.parseInt(bin.readLine());
					if(SkillChangeDirection.skillState == 2) {
						headObj1.move();
						direction1 = bin.readLine();
						headObj1.setDirection(direction1);
						headObj1.setImage(headObj1.getImage(direction1));
					}
				}
				direction2 = bin.readLine();
				
				headObj2.setDirection(direction2);
				headObj2.setImage(headObj2.getImage(direction2));

			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//初始化双缓存图片
			if(offScreenImage == null) {	//防止重复定义
				offScreenImage = frame.createImage(winWidth, winHeight);
			}
			//获取图片对应的Graphics对象
			Graphics gImage = offScreenImage.getGraphics();
			
			//填充矩形*
			gImage.setColor(Color.LIGHT_GRAY);
			gImage.fillRect(0, 0, winWidth, winHeight);
			
			//绘制网格线
			gImage.setColor(Color.black);
			for(int i = 0 ; i <= 25 ; i ++) {
				gImage.drawLine(0, i * 30, 750, i * 30);
				gImage.drawLine(i * 30 , 0 , i * 30, 750);
			}
			
			//绘制身体(注：要对bodyObjList进行反向遍历，以防止身体重叠)
			for(int i = bodyObjList1.size() - 1; i >= 0 ; i --) {
				 bodyObjList1.get(i).paintSelf(gImage);
			}
			for(int i = bodyObjList2.size() - 1; i >= 0 ; i --) {
				 bodyObjList2.get(i).paintSelf(gImage);
			}
		
			//绘制蛇头
			headObj1.paintSelf(gImage);
			headObj2.paintSelf(gImage);
			
			//绘制食物
			foodObj.paintSelf(gImage);
			
			//绘制炸弹
			//千劫的炸弹
			if(GameMenu.code1 == 1) {
				if(SkillBoom.skillState == 2) {
				boomObj = new BoomObj().getBoomObj();
				boomObj.paintSelf(gImage);
				SkillBoom.skillState = 3;
			}
			else if(SkillBoom.skillState == 1 || SkillBoom.skillState == 3) {
				boomObj.paintSelf(gImage);
				}
			}
			//初始炸弹
			else {
				boomObj.paintSelf(gImage);
			}
			
			//绘制分数								(需要绘制两个人的分数)
			//player1
			GameUtils.drawWords(gImage, "Player1:", Color.pink, 50, 750, 200);			
			GameUtils.drawWords(gImage, headObj1.getScore() + "分", Color.pink, 50, 800, 250);
			//player2
			GameUtils.drawWords(gImage, "Player2:", Color.yellow, 50, 750, 450);		
			GameUtils.drawWords(gImage, headObj2.getScore() + "分", Color.yellow, 50, 800, 500);
			
			//绘制提示语
			propmtA(gImage);
			
			//将双缓存图片绘制到窗口中
			g.drawImage(offScreenImage, 0, 0, null);
		}
	}
	
	private class MyPanelB extends JPanel{
		public void paint(Graphics g) {
			super.paint(g);	
			
			//传输2的数据给1
			try {
				//设置需要传输的数据
				direction2 = headObj2.getDirection();
				
				//发送传输数据（注意传输顺序）
				//发送技能状态
				if(GameMenu.code2 == 1) {
					pout.println(SkillBoom.skillState);		
				}
				else if(GameMenu.code2 == 2) {
					pout.println(SkillInvincible.skillState);
				}
				else if(GameMenu.code2 == 3) {
					pout.println(SkillLonger.skillState);
				}
				else if(GameMenu.code2 == 4) {
					pout.println(SkillFilp.skillState);
				}
				else if(GameMenu.code2 == 5) {
					pout.println(SkillAccelerate.skillState);
				}
				else if(GameMenu.code2 == 6) {
					pout.println(SkillChangeDirection.skillState);
					//阿波尼亚技能实现
					if(SkillChangeDirection.skillState == 2) {
						switch(direction1) {
							case"up":
								direction1 = "left";
								break;
							case"left":
								direction1 = "down";
								break;
							case"down":
								direction1 = "right";
								break;
							case"right":
								direction1 = "up";
								break;
							default:
								break;
						}
						headObj1.setDirection(direction1);
						headObj1.setImage(headObj1.getImage(direction1));
						headObj1.move();		//
						pout.println(direction1);
						SkillChangeDirection.skillState = 3;
					}
				}
				pout.println(direction2);
				
				//接收传输数据（注意传输顺序）
				FoodX = Integer.parseInt(bin.readLine());
				FoodY = Integer.parseInt(bin.readLine());
				BoomX = Integer.parseInt(bin.readLine());
				BoomY = Integer.parseInt(bin.readLine());
				if(GameMenu.code1 == 1) {
					SkillBoom.skillState = Integer.parseInt(bin.readLine());
				}
				else if(GameMenu.code1 == 2) {
					SkillInvincible.skillState = Integer.parseInt(bin.readLine());
				}
				else if(GameMenu.code1 == 3) {
					SkillLonger.skillState = Integer.parseInt(bin.readLine());
				}
				else if(GameMenu.code1 == 4) {
					SkillFilp.skillState = Integer.parseInt(bin.readLine());
				}
				else if(GameMenu.code1 == 5) {
					SkillAccelerate.skillState = Integer.parseInt(bin.readLine());
				}
				else if(GameMenu.code1 == 6) {
					SkillChangeDirection.skillState = Integer.parseInt(bin.readLine());
					if(SkillChangeDirection.skillState == 2) {
						headObj2.move();
						direction2 = bin.readLine();
						headObj2.setDirection(direction2);
						headObj2.setImage(headObj2.getImage(direction2));
					}
				}
				direction1 = bin.readLine();
				
				foodObj.setX(FoodX);
				foodObj.setY(FoodY);
				boomObj.setX(BoomX);
				boomObj.setY(BoomY);
				headObj1.setDirection(direction1);
				headObj1.setImage(headObj1.getImage(direction1));

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			//初始化双缓存图片
			if(offScreenImage == null) {	//防止重复定义
				offScreenImage = frame.createImage(winWidth, winHeight);
			}
			//获取图片对应的Graphics对象
			Graphics gImage = offScreenImage.getGraphics();
			
			//填充矩形*
			gImage.setColor(Color.LIGHT_GRAY);
			gImage.fillRect(0, 0, winWidth, winHeight);
			
			//绘制网格线
			gImage.setColor(Color.black);
			for(int i = 0 ; i <= 25 ; i ++) {
				gImage.drawLine(0, i * 30, 750, i * 30);
				gImage.drawLine(i * 30 , 0 , i * 30, 750);
			}
			
			//绘制身体(注：要对bodyObjList进行反向遍历，以防止身体重叠)
			for(int i = bodyObjList1.size() - 1; i >= 0 ; i --) {
				 bodyObjList1.get(i).paintSelf(gImage);
			}
			for(int i = bodyObjList2.size() - 1; i >= 0 ; i --) {
				 bodyObjList2.get(i).paintSelf(gImage);
			}
		
			//绘制蛇头
			headObj1.paintSelf(gImage);
			headObj2.paintSelf(gImage);
			
			//绘制食物
			foodObj.paintSelf(gImage);
			
			//绘制炸弹
			//千劫的炸弹
			if(GameMenu.code2 == 1) {
				if(SkillBoom.skillState == 2) {
				boomObj = new BoomObj().getBoomObj();
				boomObj.paintSelf(gImage);
				SkillBoom.skillState = 3;
			}
			else if(SkillBoom.skillState == 1 || SkillBoom.skillState == 3) {
				boomObj.paintSelf(gImage);
				}
			}
			//初始炸弹
			else {
				boomObj.paintSelf(gImage);
			}
			
			//绘制分数								(需要绘制两个人的分数)
			//player1
			GameUtils.drawWords(gImage, "Player1:", Color.pink, 50, 750, 200);			
			GameUtils.drawWords(gImage, headObj1.getScore() + "分", Color.pink, 50, 800, 250);
			//player2
			GameUtils.drawWords(gImage, "Player2:", Color.yellow, 50, 750, 450);		
			GameUtils.drawWords(gImage, headObj2.getScore() + "分", Color.yellow, 50, 800, 500);
			
			//绘制提示语
			propmtB(gImage);
			
			//将双缓存图片绘制到窗口中
			g.drawImage(offScreenImage, 0, 0, null);
		}
	}
	
	public JFrame getJFrame() {
		return this.frame;
	}

	public List<BodyObj> getBodyObjList(int player){
		if(player == 1)
			return this.bodyObjList1;
		else
			return this.bodyObjList2;
	}
	
	public static int getCode(int player) {
		if(player == 1)
			return GameMenu.code1;
		else
			return GameMenu.code2;
	}
	
	public static int getState(int player) {
		if(player == 1)
			return GameMenu.state1;
		else
			return GameMenu.state2;
	}
	
	public static void setState(int player) {
		if(player == 1) {
			GameMenu.state1 = 2;
			GameMenu.state2 = 3;
		}		
		else {
			GameMenu.state2 = 2;
			GameMenu.state1 = 3;
		}
			
	}
}

