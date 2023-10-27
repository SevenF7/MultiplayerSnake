package MultiplayerSnake.Client.skills;

public class SkillAccelerate implements Skills{

	//����״̬ 1.����ʹ�� 2.ʹ���� 3.��ȴ��
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
			//���������ļ�ʱ�̣߳�һ������������һ�������ʱ����
			ThreadStart start = new ThreadStart();
			ThreadTimer timer = new ThreadTimer();
			start.start();
			timer.start();
		}
		else if(SkillAccelerate.skillState == 3) {
			System.out.println("����5��CD");					//(֮����Ը�Ϊͼ����ʾ����CD,����textfield)
		}
	}

	//�����߳�
	class ThreadStart extends Thread{
		public void run(){
			cd.await5(skillTime);
		}
	}
	//��ʱ�߳�
	class ThreadTimer extends Thread{
		public void run(){
			cd.signal5(CD);
		}
	}
}
