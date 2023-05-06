package tests;

import java.io.IOException;
import java.net.URI;
import bl.*;
import org.eclipse.jetty.websocket.api.util.WSURI;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.eclipse.jetty.websocket.api.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.WebSocketServer;


public class WebSocketIntegrationTests {
    private WebSocketServer server;
    private ProductTracker tracker;
    private UserDelegate delegate;
    private WebSocketClientStub client;
    private Session session;
    
    @Before
    public void startServer() throws Exception {
    	tracker = new ProductTracker();
        delegate = new UserDelegate(tracker, new UserDao());
    	server = new WebSocketServer(tracker, delegate);
    	//tracker.start();
    	server.setPort(8090);
        server.start();
        //server.join();
        createTestConnection();
    }
    
    public void createTestConnection() throws Exception{
        //URI destUri = server.getURI().resolve("/nectar/");
        //URI wsUri = WSURI.toWebsocket(destUri);
    	System.out.println("testconnect1");
    	URI uri = URI.create("ws://localhost:8090/nectar");
    	System.out.println("testconnect2");
        client = new WebSocketClientStub();
        System.out.println("testconnect3");
        //client.run(uri, tracker, delegate);
        session = client.connect(uri, tracker, delegate);
        System.out.println("testconnect4");
        
    }
    
    @Test
    public void testNotifyUserOnProductChange() {
    	System.out.println("Step1");
    	tracker.setEndpoint(client.getEndpoint(), "1");
    	System.out.println("Step2");
    	UserVO user = new UserVO("1", "", "", "");
    	System.out.println("Step3");
    	tracker.addUser(user, "1", "testurl", "Stub");
    	System.out.println("Step4");
    	tracker.run();
    }
    
    @After
    public void closeSession() {
    	session.close();
    }
}
