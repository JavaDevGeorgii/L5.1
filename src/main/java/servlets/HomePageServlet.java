package servlets;

import accountServer.AccountServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by GEO on 28.02.16.
 */
public class HomePageServlet extends HttpServlet {

    static final Logger logger= LogManager.getLogger(HomePageServlet.class.getName());
    public static final String PAGE_URL="/home";
    private final AccountServer accountServer;

    public HomePageServlet(AccountServer accountServer) {
        this.accountServer = accountServer;
    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");

        String remove=req.getParameter("remove");

        if(remove!=null){
            accountServer.remiveUser();
            resp.getWriter().println("See you soon!");
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        long limit=accountServer.getUsersLimit();
        long count=accountServer.getUsersCount();
        logger.info("Limit: {}. Count {}", limit, count);

        if (limit>count){
            logger.info("User pass");
            accountServer.addNewUser();
            resp.getWriter().println("Hello, world!");
            resp.setStatus(HttpServletResponse.SC_OK);
        }else {
            logger.info("User were rejected");
            resp.getWriter().println("Server is closed for maintenance!");
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
