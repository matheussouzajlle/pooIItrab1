package dao;

import entity.Equipamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO extends BaseDAO {
    private Connection connection;

    public EquipamentoDAO() {
        connection = ConexaoBD.getInstance().getConnection();
    }

    public void inserirEquipamento(Equipamento equipamento) {
        String sql = "INSERT INTO Equipamento (Nome_Equipamento, Tipo) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, equipamento.getNomeEquipamento());
            stmt.setString(2, equipamento.getTipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarEquipamento(Equipamento equipamento) {
        String sql = "UPDATE Equipamento SET Nome_Equipamento = ?, Tipo = ? WHERE ID_Equipamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, equipamento.getNomeEquipamento());
            stmt.setString(2, equipamento.getTipo());
            stmt.setInt(3, equipamento.getIdEquipamento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirEquipamento(int idEquipamento) {
        String sql = "DELETE FROM Equipamento WHERE ID_Equipamento = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idEquipamento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Equipamento> listarEquipamentos() {
        List<Equipamento> equipamentos = new ArrayList<>();
        String sql = "SELECT * FROM Equipamento";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Equipamento equipamento = new Equipamento(
                        rs.getInt("ID_Equipamento"),
                        rs.getString("Nome_Equipamento"),
                        rs.getString("Tipo")
                );
                equipamentos.add(equipamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipamentos;
    }
}
