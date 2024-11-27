package Servlets.Helpers;


import dao.PessoasDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Endereco;
import utils.DataSourceSearcher;
import utils.EncriptadorDeSenha;

@WebServlet("/AdicionarPessoa")
public class AddPessoaHelper implements Helper {


    @Override
    public Object execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = EncriptadorDeSenha.encode(req.getParameter("senha"));
        String telefone = req.getParameter("telefone");
        String cpf = req.getParameter("cpf");
        String cnpj = req.getParameter("cnpj");


        Endereco endereco = new Endereco.EnderecoBuilder()
                .setLogradouro(req.getParameter("logradouro"))
                .setNumero(req.getParameter("numero"))
                .setComplemento(req.getParameter("complemento"))
                .setBairro(req.getParameter("bairro"))
                .setCep(req.getParameter("cep"))
                .setCidade(req.getParameter("cidade"))
                .setEstado(req.getParameter("estado"))
                .build();

        PessoasDao dao = new PessoasDao(DataSourceSearcher.getInstance().getDataSource());
        dao.savePessoa(nome, email, senha, telefone, cpf, cnpj, endereco);

        req.setAttribute("result", "registered");
        return "/login.jsp";
    }

}
