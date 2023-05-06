package bl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Map.Entry;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONObject;

import server.EventEndpoint;
import server.JSONMessage;

public class Receiver {
    private static String sender = "nectarupdates@outlook.com";
    private static String senderPassword = "*qSdFCG641#G";
    private static String host = "outlook.office365.com";
    private static String[] carrierEmails = {"@tmomail.com", "@vmobl.com", "@cingularme.com", "@messaging.sprintpcs.com",
        "@vtext.com", "@messaging.nextel.com"};

    private HashMap<String, UserVO> userSubscribers;
    private EventEndpoint endpoint;
    private String currentEndPointUserID;
    
    ScrapedProductVO previousProductVO;
    
    public Receiver() {
    	userSubscribers = new HashMap<String, UserVO>();
    }

    public void addUser(String userID, UserVO user){
        userSubscribers.put(userID, user);
    }

    public void setEndpoint(EventEndpoint endpoint, String userID) {
    	System.out.println("user id " + userID);
    	this.endpoint = endpoint;
    	this.currentEndPointUserID = userID;
    }
    public void closeEndpoint() {
    	this.endpoint = null;
    	this.currentEndPointUserID = null;
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

    public void receive(ScrapedProductVO productVO){
    	if(productVO == null) {
    		return;
    	}
        if (previousProductVO != productVO){
        	System.out.println("diff");
        	if(currentEndPointUserID != null) {
        		if(userSubscribers.containsKey(currentEndPointUserID)){
        			JSONObject result = new JSONObject();
        			if(previousProductVO != null) {
        				result.put("previousProductInfo", previousProductVO.encode());
        			}     		
            		result.put("currentProductInfo", productVO.encode());
            		endpoint.sendJSONMessageToSession(new JSONMessage("Product Change", result.toString()));  
        		}
        	}  
        	for (Entry<String, UserVO> entry : userSubscribers.entrySet()) {
                UserVO user = entry.getValue();
                try {
                	if(!user.userPhoneNumber.isEmpty()) {
                		sendTextMessage(productVO, user.userPhoneNumber);
                	}
                	if(!user.userEmail.isEmpty()) {
                		sendEmailMessage(productVO, user.userEmail);
                	}                	
					
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } 
        	previousProductVO = productVO;
        }
        if (previousProductVO == null){
            previousProductVO = productVO;
        }
    }
    
    private void sendTextMessage(ScrapedProductVO productVO, String number) throws MessagingException{
        //carriers accept email so send email to all carrier providers
        for(String carrier : carrierEmails){
            String address = number.replaceAll("\\D+", "") + carrier;
            sendEmailMessage(productVO, address);
        }
    }

    private void sendEmailMessage(ScrapedProductVO productVO, String emailAddress) throws MessagingException{
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
            String msg = previousProductVO.createMessageOfProductChange(productVO);

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
