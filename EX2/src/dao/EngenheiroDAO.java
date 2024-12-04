package dao;

import entity.Engenheiro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EngenheiroDAO extends BaseDAO {
    private Connection connection;

    public EngenheiroDAO() {
        connection = ConexaoBD.getInstance().getConnection();
    }

    public void inserirEngenheiro(Engenheiro engenheiro) {
        String sql = "INSERT INTO Engenheiro (Nome_Engenheiro, Especialidade) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, engenheiro.getNomeEngenheiro());
            stmt.setString(2, engenheiro.getEspecialidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarEngenheiro(Engenheiro engenheiro) {
        String sql = "UPDATE Engenheiro SET Nome_Engenheiro = ?, Especialidade = ? WHERE ID_Engenheiro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, engenheiro.getNomeEngenheiro());
            stmt.setString(2, engenheiro.getEspecialidade());
            stmt.setInt(3, engenheiro.getIdEngenheiro());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirEngenheiro(int idEngenheiro) {
        String sql = "DELETE FROM Engenheiro WHERE ID_Engenheiro = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEngenheiro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Engenheiro> listarEngenheiros() {
        List<Engenheiro> engenheiros = new ArrayList<>();
        String sql = "SELECT * FROM Engenheiro";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Engenheiro engenheiro = new Engenheiro(
                        rs.getInt("ID_Engenheiro"),
                        rs.getString("Nome_Engenheiro"),
                        rs.getString("Especialidade")
                );
                engenheiros.add(engenheiro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return engenheiros;
    }
}
