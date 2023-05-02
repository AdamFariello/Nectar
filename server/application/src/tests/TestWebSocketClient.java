package tests;

import java.net.URI;
import java.util.concurrent.Future;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import bl.EventEndpoint;

public class TestWebSocketClient {
    public void run(URI uri) throws Exception
    {
        WebSocketClient client = new WebSocketClient();

        try
        {
            client.start();
            EventEndpoint socket = new EventEndpoint();
            Future<Session> future = client.connect(socket, uri);
            Session session = future.get();
            socket.awaitClosure();
            session.close();
        }
        finally
        {
            client.stop();
        }
    }

}
