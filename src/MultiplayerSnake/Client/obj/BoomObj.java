package MultiplayerSnake.Client.obj;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import MultiplayerSnake.Client.utils.GameUtils;
import MultiplayerSnake.Client.view.GameMenu;

public class BoomObj extends GameObj{

	//Ëæ»ú*
	Random r = new Random();
	
	public BoomObj() {
		super();
	}
	
	public BoomObj(Image img , int x , int y , GameMenu frame) {
		super(img , x , y , frame);
	}
	
	//»ñÈ¡Õ¨µ¯
	public BoomObj getBoomObj() {
		return new BoomObj(GameUtils.boomImage , r.nextInt(25) * 30 , (r.nextInt(24) + 1) * 30 , this.frame);
	}
	
	@Override
	public void paintSelf(Graphics g) {
		super.paintSelf(g);
	}
}
