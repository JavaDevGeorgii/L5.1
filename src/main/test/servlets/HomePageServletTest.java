import accountServer.AccountServer;
import org.junit.Test;
import servlets.HomePageServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by GEO on 29.02.16.
 */
public class HomePageServletTest {

    private AccountServer accountServer=mock(AccountServer.class);

    private HttpServletResponse getMockedResponse(StringWriter stringWriter) throws IOException {
        HttpServletResponse response=mock(HttpServletResponse.class);
        final PrintWriter printWriter=new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        return response;
    }

    private HttpServletRequest getMockedRequest(String url){
        HttpSession httpSession=mock(HttpSession.class);
        HttpServletRequest request=mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(httpSession);
        when(request.getPathInfo()).thenReturn(url);
        return request;
    }

    @Test
    public void testRemove() throws IOException, ServletException {
        final StringWriter stringWriter=new StringWriter();
        HttpServletResponse response=getMockedResponse(stringWriter);
        HttpServletRequest request=getMockedRequest(HomePageServlet.PAGE_URL);

        when(request.getParameter("remove")).thenReturn("");

        HomePageServlet homePage= new HomePageServlet(accountServer);
        homePage.doGet(request,response);

        assertEquals("Hasta la vista!", stringWriter.toString().trim());
        verify(accountServer, times(1)).remiveUser();

    }



}