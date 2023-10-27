package MultiplayerSnake.Client.skills;

public class SkillAccelerate implements Skills{

	//技能状态 1.可以使用 2.使用中 3.冷却中
	public static int skillState = 1;
	private SkillsCD cd = new SkillsCD();
	private int skillTime;
	private int CD;
	
	public SkillAccelerate(Boolean isWanNing) {
		if(!isWanNing) {
			skillTime = 3100;
			CD = 12000;
		}
		else {
			skillTime = 5000;
			CD = 5000;
		}
	}

	@Override
	public void runSkill() {
		if(SkillAccelerate.skillState == 1) {
			//两个独立的计时线程，一个负责启动，一个负责计时唤醒
			ThreadStart start = new ThreadStart();
			ThreadTimer timer = new ThreadTimer();
			start.start();
			timer.start();
		}
		else if(SkillAccelerate.skillState == 3) {
			System.out.println("技能5在CD");					//(之后可以改为图标显示技能CD,或者textfield)
		}
	}

	//启动线程
	class ThreadStart extends Thread{
		public void run(){
			cd.await5(skillTime);
		}
	}
	//计时线程
	class ThreadTimer extends Thread{
		public void run(){
			cd.signal5(CD);
		}
	}
}
