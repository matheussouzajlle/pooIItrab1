package dao;

import entity.Operario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperarioDAO extends BaseDAO {
    private Connection connection;

    public OperarioDAO() {
        connection = ConexaoBD.getInstance().getConnection();
    }

    public void inserirOperario(Operario operario) {
        String sql = "INSERT INTO Operario (Nome_Operario, Funcao) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, operario.getNomeOperario());
            stmt.setString(2, operario.getFuncao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarOperario(Operario operario) {
        String sql = "UPDATE Operario SET Nome_Operario = ?, Funcao = ? WHERE ID_Operario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, operario.getNomeOperario());
            stmt.setString(2, operario.getFuncao());
            stmt.setInt(3, operario.getIdOperario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirOperario(int idOperario) {
        String sql = "DELETE FROM Operario WHERE ID_Operario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idOperario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Operario> listarOperarios() {
        List<Operario> operarios = new ArrayList<>();
        String sql = "SELECT * FROM Operario";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Operario operario = new Operario(
                        rs.getInt("ID_Operario"),
                        rs.getString("Nome_Operario"),
                        rs.getString("Funcao")
                );
                operarios.add(operario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operarios;
    }
}
