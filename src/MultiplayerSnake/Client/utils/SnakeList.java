package MultiplayerSnake.Client.utils;

import java.util.ArrayList;
import java.util.List;

import MultiplayerSnake.Client.skills.*;
import MultiplayerSnake.Client.view.GameMenu;
import MultiplayerSnake.Client.view.MainMenu;

public class SnakeList{

	//��ʵ��ģʽ
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
		//Ϊÿѡ�е�����Ӷ��صļ���	(����ֻ�����һ���ߵļ���)
		switch(GameMenu.code1) {
			case 1:
				this.snakeList.get(0).setSkill(new SkillBoom(isWanNing));		//ǧ��
				break;
			case 2:
				this.snakeList.get(1).setSkill(new SkillInvincible(isWanNing));	//��
				break;
			case 3:
				this.snakeList.get(2).setSkill(new SkillLonger(isWanNing));	    //÷����˹
				break;
			case 4:
				this.snakeList.get(3).setSkill(new SkillFilp(isWanNing));		//ά��ޱ
				break;
			case 5:
				this.snakeList.get(4).setSkill(new SkillAccelerate(isWanNing));	//���䡤����˹
				break;
			case 6:
				this.snakeList.get(5).setSkill(new SkillChangeDirection(isWanNing));	//��������
				break;
			default:
				break;
		}
		switch(GameMenu.code2) {
		case 1:
			this.snakeList.get(0).setSkill(new SkillBoom(isWanNing));		//ǧ��
			break;
		case 2:
			this.snakeList.get(1).setSkill(new SkillInvincible(isWanNing));	//��
			break;
		case 3:
			this.snakeList.get(2).setSkill(new SkillLonger(isWanNing));	    //÷����˹
			break;
		case 4:
			this.snakeList.get(3).setSkill(new SkillFilp(isWanNing));		//ά��ޱ
			break;
		case 5:
			this.snakeList.get(4).setSkill(new SkillAccelerate(isWanNing));	//���䡤����˹
			break;
		case 6:
			this.snakeList.get(5).setSkill(new SkillChangeDirection(isWanNing));	//��������
			break;
		default:
			break;
		}
	}
	
	public List<GameUtils> getSnakeList(){
		return this.snakeList;
	}
}
