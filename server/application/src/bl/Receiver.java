package bl;
import java.util.ArrayList;
import java.util.Properties;

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

    private ArrayList<String> userSubscribers;
    private ArrayList<String> phoneNumbers;
    private ArrayList<String> emailAddresses;
    private EventEndpoint endpoint;
    private String currentEndPointUserID;
    
    ScrapedProductVO previousProductVO;
    
    public Receiver() {
    	userSubscribers = new ArrayList<String>();
    }

    public void addUser(String userID){
        userSubscribers.add(userID);
    }

    public void setEndpoint(EventEndpoint endpoint, String userID) {
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
        if (previousProductVO == null){
            previousProductVO = productVO;
        } else if (previousProductVO != productVO){
        	//System.out.println(productVO.toString());
            //userSubscribers.forEach((k, v) -> v.notify(productVO, previousProductVO));
            //Get user email and phone and tracker settings from database
            //Send message
        	if(currentEndPointUserID != null) {
        		if(userSubscribers.contains(currentEndPointUserID)){
        			JSONObject result = new JSONObject();
            		result.put("previousProductInfo", previousProductVO.encode());
            		result.put("currentProductInfo", productVO.encode());
    		
            		endpoint.sendJSONMessageToSession(new JSONMessage("Product Change", result.toString()));  	
        		}
        	}     		
        	for (String number : phoneNumbers) {
        		try {
					sendTextMessage(productVO, number);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	for (String email : emailAddresses) {
        		try {
					sendEmailMessage(productVO, email);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
            /*try {
                sendEmailMessage(productVO, "dankedest444@gmail.com");
                //sendTextMessage(productVO, "9083706809");
            } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }*/
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
