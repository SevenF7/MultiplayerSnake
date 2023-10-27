package MultiplayerSnake.Client.utils;

import java.awt.*;

import MultiplayerSnake.Client.skills.Skills;

public class GameUtils {

	//�ߵı��
	private int code;
	//����
	private Skills skill;
	//�����ߵı��
	public GameUtils(int code) {
		this.code = code;
		this.setImage();
	}
	
	//�ĸ�������ͷ
	private Image upImage;
	private Image downImage;
	private Image leftImage;
	private Image rightImage;
	//�ߵ�����
	private Image bodyImage;
	//ʳ��
	public static Image foodImage = Toolkit.getDefaultToolkit().getImage("src/MultiplayerSnake/Client/image/food.png");
	//ը��
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
	//��������
	public static void drawWords(Graphics g , String str , Color color , int size , int x , int y) {
		g.setColor(color);
		g.setFont(new Font("���ķ���" , Font.BOLD , size));
		g.drawString(str , x , y);
	}
}
