package com.xplink.random_cm.managements;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
 
public class SendMailManager
{
		public synchronized static boolean sendMail(String userName,String passWord,String host,
				String port,String starttls,String auth,boolean debug,String socketFactoryClass,
				String fallback,String to,String subject,String text){
                Properties props = new Properties();
                //Properties props=System.getProperties();
        props.put("mail.smtp.user", userName);
        props.put("mail.smtp.host", host);
                if(!"".equals(port))
        props.put("mail.smtp.port", port);
                if(!"".equals(starttls))
        props.put("mail.smtp.starttls.enable",starttls);
        props.put("mail.smtp.auth", auth);
                if(debug){
                props.put("mail.smtp.debug", "true");
                }else{
                props.put("mail.smtp.debug", "false");          
                }
                if(!"".equals(port))
        props.put("mail.smtp.socketFactory.port", port);
                if(!"".equals(socketFactoryClass))
        props.put("mail.smtp.socketFactory.class",socketFactoryClass);
                if(!"".equals(fallback))
        props.put("mail.smtp.socketFactory.fallback", fallback);
 
        try
        {
                        Session session = Session.getDefaultInstance(props, null);
            session.setDebug(debug); 
            MimeMessage msg = new MimeMessage(session);
            msg.setText(text,"UTF-8");
            msg.setSubject(subject);
            msg.setFrom(new InternetAddress("kabuki.hao@gmail.com"));                     
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                        
            msg.saveChanges();
                        Transport transport = session.getTransport("smtp");
                        transport.connect(host, userName, passWord);
                        transport.sendMessage(msg, msg.getAllRecipients());
                        transport.close();
                        return true;
        }
        catch (Exception mex)
        {
            mex.printStackTrace();
                        return false;
        } 
        }
 
}