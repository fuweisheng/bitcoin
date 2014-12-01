package com.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * MailUtils
 * 
 * @author Rudy He
 * 
 */
public class MailUtils {

	private String fromMail;
	private String smtpHost;
	private String username;
	private String password;
	private String vurl;

	/**
	 * 验证邮箱格式
	 * 
	 * @param mail
	 * @return
	 */
	public static boolean mailValidate(String mail) {
		if (null == mail || "".equals(mail) || mail.length() < 8) {
			return false;
		}

		if (mail.indexOf("@") <= 0 || mail.indexOf(".") < 0
				|| mail.indexOf(".") == mail.length() - 1
				|| mail.indexOf("@") > mail.indexOf(".")
				|| mail.indexOf(".") - 1 <= mail.indexOf("@")) {
			return false;
		}

		return true;
	}

	/**
	 * 发送邮件
	 * 
	 * @param toMail
	 * @param subject
	 * @param text
	 */
	public void sendMailUtis(String toMail, String subject, String text) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtpHost);
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");

		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.port", 465);

		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {
					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
						return new javax.mail.PasswordAuthentication(username,
								password);
					}
				});
		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			InternetAddress address = new InternetAddress(fromMail);
			mimeMessage.setFrom(address);
			mimeMessage.addRecipient(Message.RecipientType.TO,
					new InternetAddress(toMail));
			mimeMessage.setSubject(subject);
			BodyPart mdp = new MimeBodyPart();
			mdp.setContent(text, "text/html;charset=gb2312");
			Multipart mm = new MimeMultipart();
			mm.addBodyPart(mdp);
			mimeMessage.setContent(mm);
			mimeMessage.saveChanges();
			// mimeMessage.setText(text);
			Transport.send(mimeMessage);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVurl() {
		return vurl;
	}

	public void setVurl(String vurl) {
		this.vurl = vurl;
	}

}
