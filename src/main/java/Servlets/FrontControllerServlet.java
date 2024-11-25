package Servlets;

import java.io.IOException;

import Servlets.Helpers.Helper;
import Servlets.Helpers.HelperFactory;
import com.google.gson.JsonObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ControllerServlet")
public class FrontControllerServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public FrontControllerServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Helper helper = new HelperFactory().getHelper(req);
        try {
            Object response = helper.execute(req, resp);

            if (response instanceof String) { // Presume-se que seja o caminho de uma JSP
                RequestDispatcher dispatcher = req.getRequestDispatcher(response.toString());
                dispatcher.forward(req, resp);
            } else {
                throw new ServletException("Tipo de resposta inesperado: " + response.getClass().getName());
            }
        } catch (Exception error) {
            throw new ServletException(error);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}