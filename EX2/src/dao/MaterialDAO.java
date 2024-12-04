package dao;

import entity.Material;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO extends BaseDAO {
    private Connection connection;

    public MaterialDAO() {
        connection = ConexaoBD.getInstance().getConnection();
    }

    public void inserirMaterial(Material material) {
        String sql = "INSERT INTO Material (Nome_Material, Quantidade) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, material.getNomeMaterial());
            stmt.setInt(2, material.getQuantidade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarMaterial(Material material) {
        String sql = "UPDATE Material SET Nome_Material = ?, Quantidade = ? WHERE ID_Material = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, material.getNomeMaterial());
            stmt.setInt(2, material.getQuantidade());
            stmt.setInt(3, material.getIdMaterial());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirMaterial(int idMaterial) {
        String sql = "DELETE FROM Material WHERE ID_Material = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idMaterial);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Material> listarMateriais() {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT * FROM Material";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Material material = new Material(
                        rs.getInt("ID_Material"),
                        rs.getString("Nome_Material"),
                        rs.getInt("Quantidade")
                );
                materiais.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiais;
    }
}
