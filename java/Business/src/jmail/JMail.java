package jmail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JMail {
	private String to;
	private String title;
	private String content;
	
	private String smtpHost ="smtp.163.com"; // 发送邮件服务器 
	private String username="yuanfen860913"; // 邮件服务器登录用户名 
	private String password="zwc0913"; // 邮件服务器登录密码 
	private String from="yuanfen860913@163.com";
	public boolean sendToYou() 
	{
		Properties p=System.getProperties();
		p.put("mail.smtp.host", smtpHost);
		p.put("mail.smtp.auth","true"); 
		Session session = Session.getInstance(p, null); 
		//session.setDebug(true);
		Message m=new MimeMessage(session);
		try {
			m.setText(content);
			m.setSubject(title);
			m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setFrom(new InternetAddress(from));
			Transport t=session.getTransport("smtp");
			t.connect(smtpHost, username, password);
			t.sendMessage(m, m.getAllRecipients());
		} catch (MessagingException e) {
			return false;
		}
		return true;
	}
	public void setTo(String to) {
		this.to = to;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
