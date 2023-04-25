<<<<<<< HEAD
import java.util.HashMap;
=======
package server.application;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

>>>>>>> 60d4e37 (Implemented text message sending and email sendign)

public class Receiver {
    private static String sender = "nectarupdates@outlook.com";
    private static String senderPassword = "*qSdFCG641#G";
    private static String host = "outlook.office365.com";
    private static String[] carrierEmails = {"@tmomail.com", "@vmobl.com", "@cingularme.com", "@messaging.sprintpcs.com",
        "@vtext.com", "@messaging.nextel.com"};

    ArrayList<String> userSubscribers;
    ProductVO previousProductVO;

    public void addUser(String userID){
        userSubscribers.add(userID);
    }

    //Returns if there are no more users left to notify
    public boolean removeUser(String userID){
        if (userSubscribers.size() == 0){
            return true;
            
        }else if (userSubscribers.size() == 1){
            userSubscribers.remove(userID);
            return true;
        }else{
            userSubscribers.remove(userID);
            return false;
        }
    }

    public void receive(ProductVO productVO){
        if (previousProductVO == null){
            previousProductVO = productVO;
        } else if (previousProductVO != productVO){
            //userSubscribers.forEach((k, v) -> v.notify(productVO, previousProductVO));
            //Get user email and phone and tracker settings from database
            //Send message
            try {
                sendEmailMessage(productVO, "rt576@scarletmail.rutgers.com");
                sendTextMessage(productVO, "9083706809");
            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    private void sendTextMessage(ProductVO productVO, String number) throws MessagingException{
        //carriers accept email so send email to all carrier providers
        for(String carrier : carrierEmails){
            String address = number.replaceAll("\\D+", "") + carrier;
            sendEmailMessage(productVO, address);
        }
    }

    private void sendEmailMessage(ProductVO productVO, String emailAddress) throws MessagingException{
        //int PORT = 587;
        int PORT = 587;
        Properties properties = System.getProperties();
        properties.put("mail.transport.protocol", "smtp");
    	properties.put("mail.smtp.port", PORT); 
    	properties.put("mail.smtp.starttls.enable", "true");
    	properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", sender);
        //properties.put("mail.smtp.password", senderPassword);

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() 
        {
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(sender,senderPassword);
            }
       });
        session.setDebug(true);
        Transport transport = session.getTransport();

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));
            message.setSubject("Update for " + productVO.title);

            String msg = "The product named: ";
            double diff = productVO.price - previousProductVO.price;
            if (productVO.title.indexOf(",") > 0){
                msg += productVO.title.substring(0, productVO.title.indexOf(","));
            }else{
                msg += productVO.title;
            }
            if (diff < 0){
                msg += "price has gone down";
            }else if (diff > 0){
                msg += "price has gone up";
            }
            msg += "\n Price: $" + productVO.price;
            
            if (!previousProductVO.available && productVO.available){
                if (productVO.amtInStock == -1){
                    msg += "\n is now in stock";
                }else{
                    msg += "\n is now in stock and only " + productVO.amtInStock + " are available";
                }
            }else if (previousProductVO.available && !productVO.available){
                msg += "\n is not in stock anymore";
            }

            message.setText(msg);
            //transport.connect(host, smtpUserName, smtpPassword);
            transport.connect(host, sender, senderPassword);
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        finally{
            transport.close();
        }
    }
}
