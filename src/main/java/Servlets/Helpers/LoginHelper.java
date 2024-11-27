package Servlets.Helpers;



import java.util.Optional;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.DataSourceSearcher;
@WebServlet("/Login")
public class LoginHelper implements Helper {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        String email = req.getParameter("email");
//        String password = req.getParameter("password");
//        PessoasDao userDao = new PessoasDao(DataSourceSearcher.getInstance().getDataSource());
//        Optional<User> optional = PessoasDao.puxarEmaileSenha(email, password);
//        if(optional.isPresent()) {
//            User user = optional.get();
//            HttpSession session = req.getSession();
//            session.setMaxInactiveInterval(60);
//            session.setAttribute("user", user);
//            return "/ControllerServlet?action=listActivities";
//        }else {
//            req.setAttribute("result", "loginError");
            return "/login.jsp";
//        }
    }

}