package Servlets.Helpers;

import dao.AnimalDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Animal;
import utils.DataSourceSearcher;

@WebServlet("/AtualizarAnimal")
public class UpdateAnimalHelper implements  Helper{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Integer idAnimal = Integer.parseInt(req.getParameter("idAnimal"));

        AnimalDao dao = new AnimalDao(DataSourceSearcher.getInstance().getDataSource());
        Animal animal = dao.findById(idAnimal);
        if(animal != null) {
            req.setAttribute("animal", animal);
        }
        return "/adicionarAnimal.jsp";
    }
}
