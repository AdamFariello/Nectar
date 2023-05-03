package server;
import java.net.URI;

import bl.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.server.config.JettyWebSocketServletContainerInitializer;

public class WebSocketServer {

	private static String testUrl = "https://www.amazon.com/PlayStation-PS5-Console-Ragnar%C3%B6k-Bundle-5/dp/B0BHC395WW/ref=sr_1_2?keywords=playstation+5&qid=1681961658&sprefix=play%2Caps%2C210&sr=8-2";
    private ProductTracker tracker;
    private UserDelegate delegate;
    
	public static void main(String[] args) throws Exception
    {     
        WebSocketServer server = new WebSocketServer();
        server.tracker.addUser("1", "1", testUrl, "Amazon");
        server.tracker.start();
        server.setPort(8080);
        server.start();
        server.join();
    }

    private final Server server;
    private final ServerConnector connector;

    public WebSocketServer()
    {
        server = new Server();
        connector = new ServerConnector(server);
        server.addConnector(connector);

        tracker = new ProductTracker();
        delegate = new UserDelegate(tracker, new UserDao());
        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Configure specific websocket behavior
        JettyWebSocketServletContainerInitializer.configure(context, (servletContext, wsContainer) ->
        {
            // Configure default max size
            wsContainer.setMaxTextMessageSize(65535);

            // Add websockets
            wsContainer.addMapping("/nectar/*", new EventEndpointCreator(tracker, delegate));
        });
    }

    public void setPort(int port)
    {
        connector.setPort(port);
    }

    public void start() throws Exception
    {
        server.start();
    }

    public URI getURI()
    {
        return server.getURI();
    }

    public void stop() throws Exception
    {
        server.stop();
    }

    public void join() throws InterruptedException
    {
        System.out.println("Use Ctrl+C to stop server");
        server.join();
    }
}
