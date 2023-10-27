package MultiplayerSnake.Client.obj;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import MultiplayerSnake.Client.view.GameMenu;

public class BodyObj extends GameObj implements Serializable{

	public BodyObj(Image img , int x , int y , GameMenu frame , int code) {
		super(img , x , y , frame , code);
	}
	
	@Override
	public void paintSelf(Graphics g) {
		super.paintSelf(g);
	}
}
