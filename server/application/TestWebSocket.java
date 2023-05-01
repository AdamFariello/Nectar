import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/aa")
public class TestWebSocket {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("open");
    }


    @OnClose
    public void onClose(Session session) {
        System.out.println("close");
    }

    @OnMessage
    public String onMessage(String text) {
        System.out.println("message:" + text);
        return "Server:" + text;
    }
}
