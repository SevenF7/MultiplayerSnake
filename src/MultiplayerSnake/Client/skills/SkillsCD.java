package MultiplayerSnake.Client.skills;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SkillsCD {

	//ʵ�ֻ���ָ���߳�	
	private Lock lock = new ReentrantLock();
	private Condition skillCD1 = lock.newCondition();			//ǧ�ٵļ��ܼ�ʱ�߳�
	private Condition skillCD2 = lock.newCondition();			//���ļ��ܼ�ʱ�߳�
	private Condition skillCD3 = lock.newCondition();			//÷����˹�ļ��ܼ�ʱ�߳�
	private Condition skillCD4 = lock.newCondition();			//ά��ޱ�ļ��ܼ�ʱ�߳�
	private Condition skillCD5 = lock.newCondition();			//���䡤����˹�ļ��ܼ�ʱ�߳�
	private Condition skillCD6 = lock.newCondition();			//�������ǵļ��ܼ�ʱ�߳�
	
	public void await1() {
		try {
			lock.lock();
			SkillBoom.skillState = 2;
			System.out.println("�ͷż���1");				
			skillCD1.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void signal1(int CD) {
		try {
			lock.lock();
			Thread.sleep(CD);
			SkillBoom.skillState = 1;
			skillCD1.signal();
			System.out.println("1���ܺ���");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();	
		}
	}
	
	public void await2(int skillTime) {
		try {
			lock.lock();
			SkillInvincible.skillState = 2;
			System.out.println("�ͷż���2");				
			Thread.sleep(skillTime);
			System.out.println("����2����");	
			skillCD2.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void signal2(int CD) {
		try {
			lock.lock();
			SkillInvincible.skillState = 3;
			Thread.sleep(CD);
			SkillInvincible.skillState = 1;
			skillCD2.signal();
			System.out.println("2���ܺ���");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();	
		}
	}
	
	public void await3() {
		try {
			lock.lock();
			SkillLonger.skillState = 2;
			System.out.println("�ͷż���3");					
			skillCD3.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void signal3(int CD) {
		try {
			lock.lock();
			Thread.sleep(CD);
			SkillLonger.skillState = 1;
			skillCD3.signal();
			System.out.println("3���ܺ��� ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();	
		}
	}
	
	public void await4() {
		try {
			lock.lock();
			SkillFilp.skillState = 2;
			System.out.println("�ͷż���4");					
			skillCD4.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void signal4(int CD) {
		try {
			lock.lock();
			Thread.sleep(CD);
			SkillFilp.skillState = 1;
			skillCD4.signal();
			System.out.println("4���ܺ���");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();	
		}
	}
	
	public void await5(int skillTime) {
		try {
			lock.lock();
			SkillAccelerate.skillState = 2;
			System.out.println("�ͷż���5");					
			Thread.sleep(skillTime);
			System.out.println("����5����");	
			skillCD5.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void signal5(int CD) {
		try {
			lock.lock();
			SkillAccelerate.skillState = 3;
			Thread.sleep(CD);
			SkillAccelerate.skillState = 1;
			skillCD5.signal();
			System.out.println("5���ܺ���");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();	
		}
	}
	
	public void await6() {
		try {
			lock.lock();
			SkillChangeDirection.skillState = 2;
			System.out.println("�ͷż���6");					
			skillCD6.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void signal6(int CD) {
		try {
			lock.lock();
			Thread.sleep(CD);
			SkillChangeDirection.skillState = 1;
			skillCD6.signal();
			System.out.println("6���ܺ���");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();	
		}
	}
	
	


}
