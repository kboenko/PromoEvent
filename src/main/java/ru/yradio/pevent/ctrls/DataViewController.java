package ru.yradio.pevent.ctrls;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ru.yradio.pevent.services.InteractiveService;

@WebServlet(name = "textileController", urlPatterns = {"/action"})
public class DataViewController extends HttpServlet {
    
    private static final Logger log = Logger.getLogger(DataViewController.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       log.debug("Program begins to display current data from DB to main page...");
        try {
            request.setAttribute("points", InteractiveService.getPoints());
            request.setAttribute("entities", InteractiveService.getEntities());
        } catch (SQLException | ClassNotFoundException ex) {
            log.error("SOMETHING WRONG: " + ex);
        }
        log.debug("Current data has been successfully displayed!");
        request.getRequestDispatcher("WEB-INF/pages/results.jsp").forward(request, response);
    }
}
