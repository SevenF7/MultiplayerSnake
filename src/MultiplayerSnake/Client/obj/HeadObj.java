package MultiplayerSnake.Client.obj;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import MultiplayerSnake.Client.skills.SkillAccelerate;
import MultiplayerSnake.Client.skills.SkillFilp;
import MultiplayerSnake.Client.skills.SkillInvincible;
import MultiplayerSnake.Client.skills.SkillLonger;
import MultiplayerSnake.Client.utils.GameUtils;
import MultiplayerSnake.Client.view.GameMenu;

public class HeadObj extends GameObj{

	//方向字符串，默认方向为right
	private String direction = "right";
	private List<BodyObj> bodyObjList;
	private GameUtils gameUtils;
	private Boolean isUseKey;
	public HeadObj(Image img , int x , int y , GameMenu frame , int code , int player , Boolean isUseKey) {
		super(img , x , y , frame , code , player , isUseKey);
		bodyObjList = this.frame.getBodyObjList(player);
		gameUtils = GameMenu.imageList.get(code - 1);
		//添加键盘监听器，获得键盘按键
		if(isUseKey) {
			frame.getJFrame().addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					changeDirection(e);
				}
			});
		}
		
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public String getDirection() {
		return this.direction;
	}
	
	public void setImage(Image img) {
		this.img = img;
	}
	
	public Image getImage(String direction) {
		if(direction.equals("up")) {
			return this.gameUtils.getUpImage();
		}
		else if(direction.equals("down")) {
			return this.gameUtils.getDownImage();
		}
		else if(direction.equals("right")) {
			return this.gameUtils.getRightImage();
		}
		else {
			return this.gameUtils.getLeftImage();
		}
	}

	//控制移动方向
	public void changeDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_A:
				if(!this.direction.equals("right")) {
					this.direction = "left";
					img = gameUtils.getLeftImage();
				}
				break;
			case KeyEvent.VK_D:
				if(!this.direction.equals("left")) {
					this.direction = "right";
					img = gameUtils.getRightImage();
				}
				break;
			case KeyEvent.VK_W:
				if(!this.direction.equals("down")) {
					this.direction = "up";
					img = gameUtils.getUpImage();
				}
				break;
			case KeyEvent.VK_S:
				if(!this.direction.equals("up")) {
					this.direction = "down";
					img = gameUtils.getDownImage();
				}
				break;
			default:
				break;
		}
	}
	
	//蛇头和蛇身的移动
	public void move() {
		//蛇身的移动
		//注意遍历顺序
		for(int i = bodyObjList.size() - 1 ; i >= 1 ; i --) {
			//每一个蛇身与前一个坐标相连
			bodyObjList.get(i).x = bodyObjList.get(i - 1).x;
			bodyObjList.get(i).y = bodyObjList.get(i - 1).y;
			//蛇头与身体碰撞判定
			if(this.x == bodyObjList.get(i).x && this.y == bodyObjList.get(i).y) {
				if(code == 2 && SkillInvincible.skillState == 2) {
					//若为华且在技能持续期间跳过状态判定
				}
				//失败
				else {
					GameMenu.setState(player);
				}
			}
		}
		//第一个身体与蛇头相连
		bodyObjList.get(0).x = this.x;
		bodyObjList.get(0).y = this.y;
		
		//蛇头和炸弹的碰撞判定
		BoomObj boomObj = this.frame.boomObj;
		if(this.x == boomObj.x && this.y == boomObj.y) {
			if(code == 2 && SkillInvincible.skillState == 2) {
				//若为华且在技能持续期间跳过状态判定
			}
			//失败
			else {
				GameMenu.setState(player);
			}
		}
		
		//蛇头和另一只蛇的蛇身的碰撞判定
		List<BodyObj> otherBodyObjList;
		if(player == 1) {
			otherBodyObjList = this.frame.getBodyObjList(2);
		}
		else {
			otherBodyObjList = this.frame.getBodyObjList(1);
		}
		for(int i = otherBodyObjList.size() - 1 ; i >= 1 ; i --) {
			if(this.x == otherBodyObjList.get(i).getX() && this.y == otherBodyObjList.get(i).getY()) {
				if(code == 2 && SkillInvincible.skillState == 2) {
					//若为华且在技能持续期间跳过状态判定
				}
				//失败
				else {
					GameMenu.setState(player);
				}
			}
		}
		
		
		//蛇头的移动
		//帕朵·菲利斯技能实现
		if(code == 5 && SkillAccelerate.skillState == 2) {
			switch(this.direction) {
			case"up":
				y -= height * 2;
				break;
			case"down":
				y += height * 2;
				break;
			case"left":
				x -= width * 2;
				break;
			case"right":
				x += width * 2;
				break;
			default:
				break;
			}
		}
		//一般蛇头移动
		else {
			switch(this.direction) {
			case"up":
				y -= height;
				break;
			case"down":
				y += height;
				break;
			case"left":
				x -= width;
				break;
			case"right":
				x += width;
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public void paintSelf(Graphics g) {
		super.paintSelf(g);
		
		//蛇吃食物
		FoodObj foodObj = this.frame.foodObj;
		Integer newX = null;
		Integer newY = null;
		if(this.x == foodObj.x && this.y == foodObj.y) {
			this.frame.foodObj = foodObj.getFoodObj();
			//获取蛇身体的最后一节元素，用来增长
			BodyObj lastBody = bodyObjList.get(bodyObjList.size() - 1);
			newX = lastBody.x;
			newY = lastBody.y;
			//吃到食物分数score+10
			this.setScore(this.getScore() + 10);
		}
		
		//维尔薇翻转技能的实现
				if(SkillFilp.skillState == 2 && code == 4) {
					BodyObj lastBody = bodyObjList.get(bodyObjList.size() - 1);
					this.x = lastBody.x;
					this.y = lastBody.y;
					switch(this.direction) {
						case"up":
							this.direction = "down";
							img = gameUtils.getDownImage();
							break;
						case"down":
							this.direction = "up";
							img = gameUtils.getUpImage();
							break;
						case"left":
							this.direction = "right";
							img = gameUtils.getRightImage();
							break;
						case"right":
							this.direction = "left";
							img = gameUtils.getLeftImage();
							break;
						default:
							break;
					}
					SkillFilp.skillState = 3;	
				}
				
		move();
		
		//move结束后添加新的BodyObj到bodyObjList中,增长蛇
		if(newX != null && newY != null) {
			bodyObjList.add(new BodyObj(gameUtils.getBodyImage() , newX , newY , this.frame , code));
		}
		//梅比乌斯的增长技能实现
		if(SkillLonger.skillState == 2 && code	== 3) {
			for(int i = 0 ; i < 2 ; i ++) {
				BodyObj lastBody = bodyObjList.get(bodyObjList.size() - 1);
				newX = lastBody.x;
				newY = lastBody.y;
				bodyObjList.add(new BodyObj(gameUtils.getBodyImage() , newX , newY , this.frame , code));
			}
			SkillLonger.skillState = 3;
		}
		
		//进行越界处理
		if(x < 0) {
			x = 720;
		}
		else if(x > 720) {
			x = 0;
		}
		else if(y < 0) {
			y = 720;
		}
		else if(y > 720) {
			y = 0;
		}
	}
	
}
