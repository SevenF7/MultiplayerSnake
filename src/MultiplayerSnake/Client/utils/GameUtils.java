package MultiplayerSnake.Client.utils;

import java.awt.*;

import MultiplayerSnake.Client.skills.Skills;

public class GameUtils {

	//蛇的编号
	private int code;
	//技能
	private Skills skill;
	//传入蛇的编号
	public GameUtils(int code) {
		this.code = code;
		this.setImage();
	}
	
	//四个方向蛇头
	private Image upImage;
	private Image downImage;
	private Image leftImage;
	private Image rightImage;
	//蛇的身体
	private Image bodyImage;
	//食物
	public static Image foodImage = Toolkit.getDefaultToolkit().getImage("src/MultiplayerSnake/Client/image/food.png");
	//炸弹
	public static Image boomImage = Toolkit.getDefaultToolkit().getImage("src/MultiplayerSnake/Client/image/boom.png");
	
	public void setImage() {
		this.upImage = Toolkit.getDefaultToolkit().getImage("src/MultiplayerSnake/Client/image/SnakeUp" + this.code + ".png");
		this.downImage = Toolkit.getDefaultToolkit().getImage("src/MultiplayerSnake/Client/image/SnakeDown" + this.code + ".png");
		this.leftImage = Toolkit.getDefaultToolkit().getImage("src/MultiplayerSnake/Client/image/SnakeLeft" + this.code + ".png");
		this.rightImage = Toolkit.getDefaultToolkit().getImage("src/MultiplayerSnake/Client/image/SnakeRight" + this.code + ".png");
		this.bodyImage = Toolkit.getDefaultToolkit().getImage("src/MultiplayerSnake/Client/image/SnakeBody" + this.code + ".png");
	}
	
	public Image getUpImage() {
		return this.upImage;
	}
	
	public Image getDownImage() {
		return this.downImage;
	}
	
	public Image getLeftImage() {
		return this.leftImage;
	}
	
	public Image getRightImage() {
		return this.rightImage;
	}
	
	public Image getBodyImage() {
		return this.bodyImage;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public void setSkill(Skills skill) {
		this.skill = skill;
	}
	
	public Skills getSkill() {
		return this.skill;
	}
	//绘制文字
	public static void drawWords(Graphics g , String str , Color color , int size , int x , int y) {
		g.setColor(color);
		g.setFont(new Font("华文仿宋" , Font.BOLD , size));
		g.drawString(str , x , y);
	}
}
