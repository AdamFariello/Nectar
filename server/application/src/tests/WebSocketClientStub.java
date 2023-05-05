package tests;

import java.net.URI;
import java.util.concurrent.Future;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import bl.ProductTracker;
import bl.UserDelegate;
import server.EventEndpoint;

public class WebSocketClientStub {
    public Session connect(URI uri, ProductTracker tracker, UserDelegate delegate) throws Exception{
        WebSocketClient client = new WebSocketClient();

        try
        {
            client.start();
            EventEndpoint socket = new EventEndpoint(tracker, delegate);
            Future<Session> future = client.connect(socket, uri);
            Session session = future.get();
            socket.awaitClosure();
            session.close();
            return session;
        }
        finally
        {
        	
        }
    }

}
