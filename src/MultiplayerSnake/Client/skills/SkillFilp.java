package MultiplayerSnake.Client.skills;

public class SkillFilp implements Skills{

	//技能状态 1.可以使用 2.使用中 3.冷却中
	public static int skillState = 1;
	private SkillsCD cd = new SkillsCD();
	private int CD;
	
	public SkillFilp(Boolean isWanNing) {
		if(!isWanNing) {
			CD = 12000;
		}
		else {
			CD = 5000;
		}
	}

	@Override
	public void runSkill() {
		if(SkillFilp.skillState == 1) {
			//两个独立的计时线程，一个负责启动，一个负责计时唤醒
			ThreadStart start = new ThreadStart();
			ThreadTimer timer = new ThreadTimer();
			start.start();
			timer.start();
		}
		else if(SkillFilp.skillState == 3) {
			System.out.println("技能4在CD");					//(之后可以改为图标显示技能CD,或者textfield)
		}
	}

	//启动线程
		class ThreadStart extends Thread{
			public void run(){
				cd.await4();
			}
		}
		//计时线程
		class ThreadTimer extends Thread{
			public void run(){
				cd.signal4(CD);
			}
		}
}
