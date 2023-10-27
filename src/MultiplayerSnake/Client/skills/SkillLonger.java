package MultiplayerSnake.Client.skills;

public class SkillLonger implements Skills{

	//����״̬ 1.����ʹ�� 2.ʹ���� 3.��ȴ��
	public static int skillState = 1;
	private SkillsCD cd = new SkillsCD();
	private int CD;
	
	public SkillLonger(Boolean isWanNing) {
		if(!isWanNing) {
			CD = 12000;
		}
		else {
			CD = 5000;
		}
	}
	
	@Override
	public void runSkill() {
		if(SkillLonger.skillState == 1) {
			//���������ļ�ʱ�̣߳�һ������������һ�������ʱ����
			ThreadStart start = new ThreadStart();
			ThreadTimer timer = new ThreadTimer();
			start.start();
			timer.start();
		}
		else if(SkillLonger.skillState == 3) {
			System.out.println("����3��CD");					//(֮����Ը�Ϊͼ����ʾ����CD,����textfield)
		}
	}
	
	//�����߳�
		class ThreadStart extends Thread{
			public void run(){
				cd.await3();
			}
		}
		//��ʱ�߳�
		class ThreadTimer extends Thread{
			public void run(){
				cd.signal3(CD);
			}
		}
	
}
