package org.lanqiao.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtil {
	private static String SMTPHOST="smtp.qq.com";
	private static String AUTHPASSWORD="ngljenjqaakbbhbb";
	private static String from="526718228@qq.com";
	public static void sendMail(String mailAddress,String content,String subject) {
		Properties props=new Properties();
		props.setProperty("mail.smtp.host", SMTPHOST);
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.auth", "true");
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("526718228@qq.com", "ngljenjqaakbbhbb");//客户端授权密码
			}
		};
		MailSSLSocketFactory sf=null;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sf.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf); 
		Session session=Session.getDefaultInstance(props);
		MimeMessage message=new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from,"默" , "UTF-8"));
			message.setSubject(subject);
			message.setSentDate(new Date());
			message.setContent(content, "text/html;charset=UTF-8");
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailAddress, "XZB", "UTF-8"));
			Transport tran=session.getTransport();
			tran.connect(from, AUTHPASSWORD);
			tran.sendMessage(message, message.getAllRecipients());
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
