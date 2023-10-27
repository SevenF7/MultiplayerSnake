package MultiplayerSnake.Client.utils;

import java.util.ArrayList;
import java.util.List;

import MultiplayerSnake.Client.skills.*;
import MultiplayerSnake.Client.view.GameMenu;
import MultiplayerSnake.Client.view.MainMenu;

public class SnakeList{

	//单实例模式
	private static SnakeList instance = null;
	private List<GameUtils> snakeList = new ArrayList<>();
	private Boolean isWanNing;
	
	private SnakeList() {
		isWanNing = MainMenu.isWanNing;
		setSnakeList();
	}
	
	public static SnakeList getInstance() {
		if(instance == null) {
			instance = new SnakeList();
			return instance;
		}
		else
			return instance;
	}
	
	private void setSnakeList() {
		for(int i = 0 ; i < 6 ; i ++) {
			this.snakeList.add(new GameUtils(i + 1));
		}
		//为每选中的蛇添加独特的技能	(现在只能添加一条蛇的技能)
		switch(GameMenu.code1) {
			case 1:
				this.snakeList.get(0).setSkill(new SkillBoom(isWanNing));		//千劫
				break;
			case 2:
				this.snakeList.get(1).setSkill(new SkillInvincible(isWanNing));	//华
				break;
			case 3:
				this.snakeList.get(2).setSkill(new SkillLonger(isWanNing));	    //梅比乌斯
				break;
			case 4:
				this.snakeList.get(3).setSkill(new SkillFilp(isWanNing));		//维尔薇
				break;
			case 5:
				this.snakeList.get(4).setSkill(new SkillAccelerate(isWanNing));	//帕朵·菲利斯
				break;
			case 6:
				this.snakeList.get(5).setSkill(new SkillChangeDirection(isWanNing));	//阿波尼亚
				break;
			default:
				break;
		}
		switch(GameMenu.code2) {
		case 1:
			this.snakeList.get(0).setSkill(new SkillBoom(isWanNing));		//千劫
			break;
		case 2:
			this.snakeList.get(1).setSkill(new SkillInvincible(isWanNing));	//华
			break;
		case 3:
			this.snakeList.get(2).setSkill(new SkillLonger(isWanNing));	    //梅比乌斯
			break;
		case 4:
			this.snakeList.get(3).setSkill(new SkillFilp(isWanNing));		//维尔薇
			break;
		case 5:
			this.snakeList.get(4).setSkill(new SkillAccelerate(isWanNing));	//帕朵·菲利斯
			break;
		case 6:
			this.snakeList.get(5).setSkill(new SkillChangeDirection(isWanNing));	//阿波尼亚
			break;
		default:
			break;
		}
	}
	
	public List<GameUtils> getSnakeList(){
		return this.snakeList;
	}
}
