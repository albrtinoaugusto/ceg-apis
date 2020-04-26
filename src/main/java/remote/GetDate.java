package remote;

import api.date.DateTime;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marrengula
 */
@WebServlet("/get-date")
public class GetDate extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        String type = request.getParameter("type");
        getDate(type, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        String type = request.getParameter("type");
        getDate(type, request, response);
    }

    private void getDate(String type, HttpServletRequest request, HttpServletResponse response) throws IOException
    {

        DateTime dateTime = new DateTime();

        if (null == type)
        {
            response.getWriter().write(dateTime.getFullDateDash());
        }
        else
        {
            switch (type)
            {
                case "date":
                    response.getWriter().write(dateTime.getDateDash());
                    break;
                case "time":
                    response.getWriter().write(dateTime.getTimeWithMinutes());
                    break;
                case "time-secs":
                    response.getWriter().write(dateTime.getTimeWithSeconds());
                    break;
                case "year":
                    response.getWriter().write(dateTime.getYear() + "");
                    break;
                case "month":
                    response.getWriter().write(dateTime.getMonthInt() + "");
                    break;
                case "day":
                    response.getWriter().write(dateTime.getDayInt() + "");
                    break;
                default:
                    response.getWriter().write(dateTime.getFullDateDash());
                    break;
            }
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
