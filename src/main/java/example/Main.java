package example;

import accountServer.AccountServer;
import accountServer.AccountServerController;
import accountServer.AccountServerControllerMBean;
import accountServer.AccountServerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.HomePageServlet;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * Created by GEO on 28.02.16.
 */
public class Main {

    static final Logger logger = LogManager.getLogger(Main.class.getName());
    public static void main(String []args) throws Exception {
        if(args.length!=1){
            logger.error("Use port as the first argument");
            System.exit(1);
        }

        String portString=args[0];
        int port=Integer.parseInt(portString);
        logger.info("Starting at http://127.0.0.1:"+portString);

        AccountServer accountServer=new AccountServerImpl(1);
        AccountServerControllerMBean serverStstistic = new AccountServerController(accountServer);
        MBeanServer mbs= ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName=new ObjectName("ServerManager:type=AccountServerController");
        mbs.registerMBean(serverStstistic, objectName);

        Server server=new Server(port);
        ServletContextHandler context=new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new HomePageServlet(accountServer)), HomePageServlet.PAGE_URL);

        ResourceHandler handler=new ResourceHandler();
        handler.setDirectoriesListed(true);
        handler.setResourceBase("static");

        HandlerList handlerList=new HandlerList();
        handlerList.setHandlers(new Handler[]{handler,context});
        server.setHandler(handlerList);

        server.start();
        logger.info("Server started");

        server.join();

    }
}
