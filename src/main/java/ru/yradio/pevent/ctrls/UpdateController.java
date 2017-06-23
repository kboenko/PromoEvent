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

@WebServlet(name = "updateValue", urlPatterns = {"/update"})
public class UpdateController extends HttpServlet {
    
    private static final Logger log = Logger.getLogger(DataViewController.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String opt = request.getParameter("shop");
        
        log.debug("Sending parameters for updating...");
        
        try {
            InteractiveService.updateEntity(id,opt);
        } catch (SQLException | ClassNotFoundException ex) {
            log.error("SOMETHING WRONG! " + ex);
        }
        response.sendRedirect("action");
    }
}
