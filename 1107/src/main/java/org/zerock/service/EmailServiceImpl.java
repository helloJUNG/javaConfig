package org.zerock.service;

import java.util.logging.Logger;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class EmailServiceImpl implements EmailService {

	
	 @Autowired
	 JavaMailSender javaMailSender;
	 
	 /* (non-Javadoc)
	  * @see org.uniworks.groupware.service.EmailSender#emailSender(java.lang.Object)
	  */
	 @Override
	 public <T> boolean emailSender(T report) {
	  // TODO Auto-generated method stub
	  log.debug("Sending report by email....");
	  boolean retVal = false;
	  
	  try {
	            final String emailTo = "test01@gmail.com";
	            final String emailFrom = "test01@naver.com";
	            final String subject = "test subject";
	            final String message = (String) report;

	            javaMailSender.send(new MimeMessagePreparator() {

	                @Override
	                public void prepare(MimeMessage paramMimeMessage) throws Exception {
	                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(paramMimeMessage, true, "UTF-8");

	                    mimeMessageHelper.setTo(emailTo);
	                    mimeMessageHelper.setFrom(emailFrom);
	                    mimeMessageHelper.setSubject(subject);
	                    mimeMessageHelper.setText(message);

	                    /*
	                    final File file = new File("test filename");

	                    mimeMessageHelper.addAttachment(MimeUtility.encodeText("filename"), new InputStreamSource() {

	                        @Override
	                        public InputStream getInputStream() throws IOException {
	                            // TODO Auto-generated method stub
	                            return new FileInputStream(file);
	                        }
	                    });
	     */                   
	                };
	            });

	            retVal = true;
	        } catch (Exception e) {
	            log.error("Can't send email... " + e.getMessage(), e);
	        }
	  
	  return retVal;
	 }

}
