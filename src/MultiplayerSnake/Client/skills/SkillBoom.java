package MultiplayerSnake.Client.skills;


public class SkillBoom implements Skills{
	
	//����״̬ 1.����ʹ�� 2.ʹ���� 3.��ȴ��
	public static int skillState = 1;
	private SkillsCD cd = new SkillsCD();
	private int CD;
	
	public SkillBoom(Boolean isWanNing) {
		if(!isWanNing) {
			CD = 12000;
		}
		else {
			CD = 5000;
		}
	}

	@Override
	public void runSkill() {
		if(SkillBoom.skillState == 1) {
			//���������ļ�ʱ�̣߳�һ������������һ�������ʱ����
			ThreadStart start = new ThreadStart();
			ThreadTimer timer = new ThreadTimer();
			start.start();
			timer.start();
		}
		else if(SkillBoom.skillState == 3) {
			System.out.println("����1��CD");					//(֮����Ը�Ϊͼ����ʾ����CD,����textfield)
		}
	}
	
	//�����߳�
	class ThreadStart extends Thread{
		public void run(){
			cd.await1();
		}
	}
	//��ʱ�߳�
	class ThreadTimer extends Thread{
		public void run(){
			cd.signal1(CD);
		}
	}

}
