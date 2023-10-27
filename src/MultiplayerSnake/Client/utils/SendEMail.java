package MultiplayerSnake.Client.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEMail {

	private int GameState;
	private static Boolean sendOnce1 = true;
	private static Boolean sendOnce2 = true;
	
		public SendEMail(int GameState){
			this.GameState = GameState;
		}
	
		public void send() {
			 // 收件人电子邮箱
		      String to = "3314266280@qq.com";
		 
		      // 发件人电子邮箱
		      String from = "3314266280@qq.com";
		 
		      // 指定发送邮件的主机为 smtp.qq.com
		      String host = "smtp.qq.com";  //QQ 邮件服务器
		 
		      // 获取系统属性
		      Properties properties = System.getProperties();
		 
		      // 设置邮件服务器
		      properties.setProperty("mail.smtp.host", host);
		 
		      properties.put("mail.smtp.auth", "true");
		      // 获取默认session对象
		      Session session = Session.getDefaultInstance(properties,new Authenticator(){
		        public PasswordAuthentication getPasswordAuthentication()
		        {
		         return new PasswordAuthentication("3314266280@qq.com", "lotfaqzfkwppdafc"); //发件人邮件用户名、授权码
		        }
		       });
		 
		      try{
		         // 创建默认的 MimeMessage 对象
		         MimeMessage message = new MimeMessage(session);
		 
		         // Set From: 头部头字段
		         message.setFrom(new InternetAddress(from));
		 
		         // Set To: 头部头字段
		         message.addRecipient(Message.RecipientType.TO,
		                                  new InternetAddress(to));
		 

		         Date nowTime = new Date();
		         SimpleDateFormat matter = new SimpleDateFormat(
		                 " yyyy年MM月dd日E HH时mm分ss秒");
		         System.out.println(matter.format(nowTime));
		         //发送失败消息
		         if(this.GameState == 2 && this.sendOnce1) {
		        	 // Set Subject: 头部头字段
		        	 message.setSubject("对不起，你输了呦");
		 
		        	 // 设置消息体
		        	 message.setText("您在" + matter.format(nowTime) + "输掉了一场贪吃蛇比赛");
		        	
		        	 // 发送消息
		        	 Transport.send(message);
		        	 System.out.println("Sent message successfully....");
		        	 this.sendOnce1 = !this.sendOnce1;
		         }
		         else if(this.GameState == 3 && this.sendOnce2) {
		        	// Set Subject: 头部头字段
		        	 message.setSubject("恭喜你，你赢了呦");
		 
		        	 // 设置消息体
		        	 message.setText("您在" + matter.format(nowTime) + "赢得了一场贪吃蛇比赛");
		        	 
		        	 // 发送消息
		        	 Transport.send(message);
		        	 System.out.println("Sent message successfully....");
		        	 this.sendOnce2 = !this.sendOnce2;
		         }
		        
		 
		         
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
		}
}
