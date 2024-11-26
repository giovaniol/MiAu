package dao;


import model.Adotante;
import model.Endereco;
import utils.EncriptadorDeSenha;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PessoasDao {

    private DataSource dataSource;

    public PessoasDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    public Optional<Adotante> getUserByEmailAndPassword(String email, String password) {
        String passwordEncripted = EncriptadorDeSenha.encode(password);

        String sql = "select id, nome, email, senha from adotante where email=? and senha=?";
        Optional<Adotante> optional = Optional.empty();
        try (Connection con = dataSource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, passwordEncripted);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Adotante adotante = new Adotante();
                    adotante.setId(rs.getInt(1));
                    adotante.setNome(rs.getString(2));
                    adotante.setEmail(rs.getString(3));
                    optional = Optional.of(adotante);
                }
            }
            return optional;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Erro durante a consulta no BD para conseguir email e a senha", sqlException);
        }
    }

    public void savePessoa(String nome, String email, String senha, String telefone, String cpf, String cnpj, Endereco endereco) {
        String sqlPessoa = "INSERT INTO user (nome, email, senha, telefone, cpf, cnpj) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlEndereco = "INSERT INTO endereco (logradouro, numero, complemento, bairro, cep, cidade, estado, id_pessoa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement psPessoa = con.prepareStatement(sqlPessoa, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preencherPreparedStatementPessoa(psPessoa, nome, email, senha, telefone, cpf, cnpj);
                psPessoa.executeUpdate();

                ResultSet rs = psPessoa.getGeneratedKeys();
                if (rs.next()) {
                    int userId = rs.getInt(1);

                    try (PreparedStatement psEndereco = con.prepareStatement(sqlEndereco)) {
                        preencherPreparedStatementEndereco(psEndereco, endereco, userId);
                        psEndereco.executeUpdate();
                    }
                } else {
                    throw new RuntimeException("Erro ao obter o ID da pessoa");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro: não foi possível salvar a pessoa", e);
        }
    }

    public void delete(int id) {
        String sqlUser = "DELETE FROM user WHERE id = ?";
        String sqlEndereco = "DELETE FROM endereco WHERE id_pessoa = ?";

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement psEndereco = con.prepareStatement(sqlEndereco)) {
                psEndereco.setInt(1, id);
                psEndereco.executeUpdate();
            }

            try (PreparedStatement psCliente = con.prepareStatement(sqlUser)) {
                psCliente.setInt(1, id);
                psCliente.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir cliente", e);
        }
    }

    public Adotante findById(int id) {
        Adotante adotante = null;
        String sql = "SELECT u.id, u.nome, u.email, u.telefone, u.cpf, "
                + "e.logradouro, e.numero, e.complemento, e.bairro, e.cep, e.cidade, e.estado "
                + "FROM user u JOIN endereco e ON c.id = e.id_pessoa WHERE u.id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    adotante = new Adotante();
                    adotante.setId(rs.getInt("id"));
                    adotante.setNome(rs.getString("nome"));
                    adotante.setEmail(rs.getString("email"));
                    adotante.setTelefone(rs.getString("telefone"));
                    adotante.setCpf(rs.getString("cpf"));


                    Endereco endereco = new Endereco.EnderecoBuilder()
                            .setLogradouro(rs.getString("logradouro"))
                            .setNumero(rs.getString("numero"))
                            .setComplemento(rs.getString("complemento"))
                            .setBairro(rs.getString("bairro"))
                            .setCep(rs.getString("cep"))
                            .setCidade(rs.getString("cidade"))
                            .setEstado(rs.getString("estado"))
                            .build();

                    adotante.setEndereco(endereco);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID", e);
        }

        return adotante;
    }

    public void update(int id, String nome, String email, String senha,  String telefone, String cpf, Endereco endereco) {
        String sqlAdotante = "UPDATE adotante SET nome = ?, email = ?, senha = ?,  telefone = ?, cpf = ? WHERE id = ?";
        String sqlEndereco = "UPDATE endereco SET logradouro = ?, numero = ?, complemento = ?, bairro = ?, cep = ?, cidade = ?, estado = ? WHERE id_pessoa = ?";

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement psCliente = con.prepareStatement(sqlAdotante)) {
                preencherPreparedStatementAdotante(psCliente, nome, email, telefone, cpf, id);
                psCliente.executeUpdate();
            }

            try(PreparedStatement psEndereco = con.prepareStatement(sqlEndereco)) {
                preencherPreparedStatementEndereco(psEndereco, endereco, id);
                psEndereco.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente", e);
        }


    }


    private void preencherPreparedStatementEndereco(PreparedStatement ps, Endereco endereco, int id) throws SQLException {
        ps.setString(1, endereco.getLogradouro());
        ps.setString(2, endereco.getNumero());
        ps.setString(3, endereco.getComplemento());
        ps.setString(4, endereco.getBairro());
        ps.setString(5, endereco.getCep());
        ps.setString(6, endereco.getCidade());
        ps.setString(7, endereco.getEstado());
        ps.setInt(8, id);
    }

    private void preencherPreparedStatementAdotante(PreparedStatement ps, String nome, String email, String telefone, String cpf, int id) throws SQLException {
        ps.setString(1, nome);
        ps.setString(2, email);
        ps.setString(3, telefone);
        ps.setString(4, cpf);
        ps.setInt(5, id);
    }

    private void preencherPreparedStatementPessoa(PreparedStatement ps, String nome, String email, String senha, String telefone, String cpf, String cnpj) throws SQLException {
        ps.setString(1, nome);
        ps.setString(2, email);
        ps.setString(3, senha);
        ps.setString(4, telefone);

        if (cpf == null || cpf.isEmpty()) {
            ps.setNull(5, java.sql.Types.VARCHAR);
        } else {
            ps.setString(5, cpf);
        }

        if (cnpj == null || cnpj.isEmpty()) {
            ps.setNull(6, java.sql.Types.VARCHAR);
        } else {
            ps.setString(6, cnpj);
        }
    }




}