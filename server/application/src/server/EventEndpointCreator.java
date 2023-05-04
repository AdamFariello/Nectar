package server;
import org.eclipse.jetty.websocket.server.JettyServerUpgradeRequest;
import org.eclipse.jetty.websocket.server.JettyServerUpgradeResponse;
import org.eclipse.jetty.websocket.server.JettyWebSocketCreator;
import bl.ProductTracker;
import bl.UserDelegate;

public class EventEndpointCreator implements JettyWebSocketCreator
{
	private ProductTracker tracker;
	private UserDelegate delegate;
	public EventEndpointCreator(ProductTracker tracker, UserDelegate delegate) {
		super();
		this.tracker = tracker;
		this.delegate = delegate;
	}
	
    @Override
    public Object createWebSocket(JettyServerUpgradeRequest jettyServerUpgradeRequest, JettyServerUpgradeResponse jettyServerUpgradeResponse)
    {
        return new EventEndpoint(tracker, delegate);
    }
}
