package MultiplayerSnake.Client.obj;

import java.awt.*;

import MultiplayerSnake.Client.view.GameMenu;

public class GameObj {

	//游戏父类属性：图片，所在坐标xy，物体长度和宽度，所在GameMenu,蛇的code,蛇的分数,玩家编号,是否启用键盘监听器
	protected Image img;
	protected int x;
	protected int y;
	protected int width = 30;
	protected int height = 30;
	protected GameMenu frame;
	protected int code;
	protected int score = 0;
	protected int player;
	protected Boolean isUseKey;
	
	//空构造方法
	public GameObj() {
		
	}
	//完全构造方法
	public GameObj(Image img , int x , int y , int width , int height , GameMenu frame , int code , int score , int player , Boolean isUseKey) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.frame = frame;
		this.score = score;
		this.player = player;
		this.isUseKey = isUseKey;
	}
	//蛇头的构造方法
	public GameObj(Image img , int x , int y , GameMenu frame , int code , int player , Boolean isUseKey) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.frame = frame;
		this.code = code;
		this.player = player;
		this.isUseKey = isUseKey;
	}
	//蛇身的构造方法
	public GameObj(Image img , int x , int y , GameMenu frame , int code) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.frame = frame;
		this.code = code;
	}
	//食物的构造方法
	public GameObj(Image img , int x , int y , GameMenu frame) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.frame = frame;
	}
	
	//get和set方法
	public void setImg(Image img) {
		this.img = img;
	}
	
	public Image getImg() {
		return this.img;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setFrame(GameMenu frame) {
		this.frame = frame;
	}
	
	public GameMenu getFrame() {
		return this.frame;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return this.score;
	}
	
	//绘制自身的方法
	public void paintSelf(Graphics g) {
		g.drawImage(img, x, y, null);
	}
}
