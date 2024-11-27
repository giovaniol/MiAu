package Servlets.Helpers;

import dao.AnimalDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Status;
import utils.DataSourceSearcher;

@WebServlet("/AdicionarAnimal")
public class AddAnimalHelper implements  Helper {

    @Override
    public Object execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        AnimalDao dao = new AnimalDao(DataSourceSearcher.getInstance().getDataSource());
        if(dao.saveAnimal(req.getParameter("nome"), req.getParameter("genero"), Status.valueOf(req.getParameter("status")))){
            req.setAttribute("result", "registered");


        }else{
            req.setAttribute("result", "notRegistered");

        }


        return "/home.jsp";
    }

}
