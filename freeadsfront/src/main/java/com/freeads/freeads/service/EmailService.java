package com.freeads.freeads.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.freeads.freeads.repository.EmailRepository;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.UUID;
import java.security.MessageDigest;
import org.apache.commons.codec.binary.Hex;

@Service
public class EmailService implements IEmailService
{
	@Autowired
    private JavaMailSender javaMailSender;

	@Autowired
	private EmailRepository emailRepository;

	@Override 
	public boolean SendVerificationMail( String destinationEmailAddress, String recipientName, long recipientUserId )
	{
		try
		{
			UUID uuid = UUID.randomUUID();
			MessageDigest salt = MessageDigest.getInstance("SHA-256");
			salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
			String token = Hex.encodeHexString( salt.digest() );

			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);  // true = multipart message
			helper.setTo( destinationEmailAddress );
			helper.setSubject( "Verification email" );
			
			String email_content = "<html>\n" +
					"<body>\n" +
						"<p>Hi, " + recipientName + ".<br>\n" +
						"If you registered in our shop with this email<br>\n" +
						"<a href=\"http://192.168.87.141:9779/VerifyEmailAddress?token=" + token + "\">Please click here to verify.</a>\n" +
						"</p>\n" +
					"</body>\n" +
				"</html>";

			// default = text/plain
			// true = text/html
			helper.setText( email_content, true );
			javaMailSender.send( message );

			emailRepository.assignVerificationTokenToUser( token, recipientUserId );

			return true;
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean VerifyEmailAddress( String token )
	{
		return emailRepository.verifyEmailAddress( token );
	}

	@Override
	public void SendProductStatusNotifMail( String userFirstName, String userLastName, long itemId  )
	{
		String[] itemOwnerDetails = emailRepository.getItemOwnerEmailAddress( itemId );
		String itemOwnerMailAddress = itemOwnerDetails[0];
		String itemOwnerFirstName = itemOwnerDetails[1];
		String itemOwnerLastName = itemOwnerDetails[2];
		String itemName = itemOwnerDetails[3];

		try
		{
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);  // true = multipart message
			helper.setTo( itemOwnerMailAddress );
			helper.setSubject( "Product Ad Status Notification" );
			
			String email_content = "<html>\n" +
					"<body>\n" +
						"<p>Hi, " + itemOwnerFirstName + " " + itemOwnerLastName + ".<br>\n" +
						"Our customer " + userFirstName + " " + userLastName + " has added your product: " + itemName + " to his favourites<br>\n" +
						"</p>\n" +
					"</body>\n" +
				"</html>";

			// default = text/plain
			// true = text/html
			helper.setText( email_content, true );
			javaMailSender.send( message );

		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
	}
}
