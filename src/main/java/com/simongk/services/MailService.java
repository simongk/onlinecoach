package com.simongk.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.simongk.domain.Person;

@Service
public class MailService {

	private JavaMailSender javaMailSender;
	private SimpleMailMessage mail;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendWithAttachment(Person person){
		MimeMessage message = javaMailSender.createMimeMessage();
		baseEmail(person);
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setFrom(mail.getFrom());
			helper.setTo(mail.getTo());
			helper.setSubject(mail.getSubject());
			helper.setText(mail.getText());
			
			String documentName = "plan.txt";
			FileSystemResource resource = new FileSystemResource(documentName);
			helper.addAttachment(resource.getFilename(), resource);
			javaMailSender.send(message);
		
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

	private SimpleMailMessage baseEmail(Person person) {
		mail = new SimpleMailMessage();
		mail.setTo(person.getEmailAddress());
		mail.setFrom("simongk95@gmail.com");
		mail.setSubject("Plan");
		mail.setText("Plan w zalaczniku, w razie pytan pisz.");
		return mail;
	}

}
