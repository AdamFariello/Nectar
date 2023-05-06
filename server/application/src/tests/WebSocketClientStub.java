package tests;

import java.net.URI;
import java.util.concurrent.Future;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import bl.ProductTracker;
import bl.UserDelegate;
import server.EventEndpoint;

public class WebSocketClientStub {
	private EventEndpoint socket;
	
	public void run(URI uri, ProductTracker tracker, UserDelegate delegate) throws Exception
    {
        WebSocketClient client = new WebSocketClient();

        try
        {
            client.start();
            // The socket that receives events
            EventEndpoint socket = new EventEndpoint(tracker, delegate);
            // Attempt Connect
            Future<Session> fut = client.connect(socket, uri);
            // Wait for Connect
            Session session = fut.get();

            // Send a message
            session.getRemote().sendString("Hello");

            // Send another message
            session.getRemote().sendString("Goodbye");

            // Wait for other side to close
            socket.awaitClosure();

            // Close session
            session.close();
        }
        finally
        {
            client.stop();
        }
    }
    public Session connect(URI uri, ProductTracker tracker, UserDelegate delegate) throws Exception{
        WebSocketClient client = new WebSocketClient();

        try
        {
            client.start();
            socket = new EventEndpoint(tracker, delegate);
            Future<Session> future = client.connect(socket, uri);
            Session session = future.get();
            //socket.awaitClosure();
            //session.close();
            return session;
        }
        finally
        {
        	
        }
    }
    
    public EventEndpoint getEndpoint() {
    	return socket;
    }

}
