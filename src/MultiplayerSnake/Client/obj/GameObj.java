package MultiplayerSnake.Client.obj;

import java.awt.*;

import MultiplayerSnake.Client.view.GameMenu;

public class GameObj {

	//��Ϸ�������ԣ�ͼƬ����������xy�����峤�ȺͿ�ȣ�����GameMenu,�ߵ�code,�ߵķ���,��ұ��,�Ƿ����ü��̼�����
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
	
	//�չ��췽��
	public GameObj() {
		
	}
	//��ȫ���췽��
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
	//��ͷ�Ĺ��췽��
	public GameObj(Image img , int x , int y , GameMenu frame , int code , int player , Boolean isUseKey) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.frame = frame;
		this.code = code;
		this.player = player;
		this.isUseKey = isUseKey;
	}
	//����Ĺ��췽��
	public GameObj(Image img , int x , int y , GameMenu frame , int code) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.frame = frame;
		this.code = code;
	}
	//ʳ��Ĺ��췽��
	public GameObj(Image img , int x , int y , GameMenu frame) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.frame = frame;
	}
	
	//get��set����
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
	
	//��������ķ���
	public void paintSelf(Graphics g) {
		g.drawImage(img, x, y, null);
	}
}
