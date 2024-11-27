package dao;


import model.Animal;
import model.Status;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDao {

    private DataSource dataSource;

    public AnimalDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public List<Animal> listAnimal() {
        List<Animal> animais = new ArrayList<>();
        String sql = "SELECT a.id, a.nome, a.status, a.genero "
                + "FROM animal a ";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setNome(rs.getString("nome"));
                animal.setStatus(Status.valueOf(rs.getString("status")));
                animal.setGenero(rs.getString("genero"));

                animais.add(animal);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar animais", e);
        }

        return animais;

    }


    public boolean saveAnimal(String nome, String genero, Status status) {
        String sql = "INSERT INTO animal (nome, status, genero) VALUES (?, ?, ?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            preencherPreparedStatementAnimal(ps, nome, genero, status);

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas > 0) {

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGerado = rs.getInt(1);

                    }
                }
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro: não foi possível salvar o animal", e);
        }

        return false;
    }



    public void delete(int id) {
        String sqlUser = "DELETE FROM animal WHERE id = ?";


        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement psAnimal = con.prepareStatement(sqlUser)) {
                psAnimal.setInt(1, id);
                psAnimal.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir animal", e);
        }
    }

    public Animal findById(int id) {
        Animal animal = null;
        String sql = "SELECT a.id, a.nome, a.genero, a.status "
                + "FROM animal a WHERE a.id = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    animal = new Animal();
                    animal.setId(rs.getInt("id"));
                    animal.setNome(rs.getString("nome"));
                    animal.setGenero(rs.getString("genero"));
                    animal.setStatus(Status.valueOf(rs.getString("status")));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar animal por ID", e);
        }

        return animal;
    }

    public void update(int id, String nome, String genero, Status status) {
        String sqlAdotante = "UPDATE animal SET nome = ?, status = ?, genero = ? WHERE id = ?";

        try (Connection con = dataSource.getConnection()) {
            try (PreparedStatement psAnimal = con.prepareStatement(sqlAdotante)) {
                preencherPreparedStatementAnimal(psAnimal, nome, genero, status);
                psAnimal.executeUpdate();
            }


        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar animal", e);
        }

    }

    private void preencherPreparedStatementAnimal(PreparedStatement ps, String nome, String genero, Status status) throws SQLException {
        ps.setString(1, nome);
        ps.setString(2, String.valueOf(status));
        ps.setString(3, genero);


    }
}