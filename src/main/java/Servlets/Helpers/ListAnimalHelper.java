package Servlets.Helpers;

import dao.AnimalDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Animal;
import utils.DataSourceSearcher;

import java.util.List;
@WebServlet("/ListarAnimais")
public class ListAnimalHelper implements Helper {


    @Override
    public Object execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        AnimalDao dao =
                new AnimalDao(DataSourceSearcher.getInstance().getDataSource());
        List<Animal> listaDeAnimais =
                dao.listAnimal();
        req.setAttribute("listaDeAnimais", listaDeAnimais);
        return "/home.jsp";
    }

}

