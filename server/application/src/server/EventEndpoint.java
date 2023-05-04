package server;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.StatusCode;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import bl.ProductTracker;
import bl.UserDelegate;
import org.json.*;

public class EventEndpoint extends WebSocketAdapter
{
    //private static final Logger LOG = LoggerFactory.getLogger(EventEndpoint.class);
    private final CountDownLatch closureLatch = new CountDownLatch(10000);
    private ProductTracker tracker;
	private UserDelegate delegate;
    
    public EventEndpoint(ProductTracker tracker, UserDelegate delegate) {
    	super();
    	this.tracker = tracker;
		this.delegate = delegate;
    }

    @Override
    public void onWebSocketConnect(Session sess)
    {
        super.onWebSocketConnect(sess);
        System.out.println("Endpoint connected: " + sess);
    }

    @Override
    public void onWebSocketText(String message)
    {
        super.onWebSocketText(message);
        if(isValidJSON(message)) {
        	System.out.println("Received JSON message: " + message);
        	JSONObject obj = new JSONObject(message);
            JSONMessage request = new JSONMessage(obj.get("message").toString(), obj.get("data").toString());
			JSONMessage result = delegate.handleJSONRequest(request);     
			try {
				getSession().getRemote().sendString(result.encode());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        if (message.toLowerCase(Locale.US).contains("bye"))
        {
        	//getSession().getRemote().sendString("Hello");
            getSession().close(StatusCode.NORMAL, "Thanks");
        }
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason)
    {
        super.onWebSocketClose(statusCode, reason);
        System.out.printf("Socket Closed: [%d] " + reason, statusCode);
        closureLatch.countDown();
    }

    @Override
    public void onWebSocketError(Throwable cause)
    {
        super.onWebSocketError(cause);
        cause.printStackTrace(System.err);
    }

    public void awaitClosure() throws InterruptedException
    {
        System.out.println("Awaiting closure from remote");
        closureLatch.await();
    }
    
    private boolean isValidJSON(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException e) {
            return false;
        }
        return true;
    }
}