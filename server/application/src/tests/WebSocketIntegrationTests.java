package tests;

import java.net.URI;
import bl.*;
import org.eclipse.jetty.websocket.api.util.WSURI;
import org.junit.Test;

import junit.framework.*;
import server.WebSocketServer;

public class WebSocketIntegrationTests {
    private WebSocketServer server;

    @Test
    public void testWebSocketServer() throws Exception{
        URI destUri = server.getURI().resolve("/nectar/");
        URI wsUri = WSURI.toWebsocket(destUri);
        TestWebSocketClient client = new TestWebSocketClient();
        client.run(wsUri);
        
    }

}
