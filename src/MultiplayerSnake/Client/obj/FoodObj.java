package MultiplayerSnake.Client.obj;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import MultiplayerSnake.Client.utils.GameUtils;
import MultiplayerSnake.Client.view.GameMenu;

public class FoodObj extends GameObj{

	//随机*
	Random r = new Random();
	
	public FoodObj() {
		super();
	}
	
	public FoodObj(Image img , int x , int y , GameMenu frame) {
		super(img , x , y , frame);
	}
	
	//获取食物
	public FoodObj getFoodObj() {
		return new FoodObj(GameUtils.foodImage , r.nextInt(25) * 30 , (r.nextInt(24) + 1) * 30 , this.frame);
	}
	
	@Override
	public void paintSelf(Graphics g) {
		super.paintSelf(g);
	}
}
