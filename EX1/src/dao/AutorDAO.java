package dao;

import entity.Autor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutorDAO extends BaseDAO {
        private Connection connection;

        public AutorDAO() {
            connection = ConexaoBD.getInstance().getConnection();
        }

        public void inserirAutor(Autor autor) {
            String sql = "INSERT INTO Autor (Nome, Nacionalidade) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, autor.getNome());
                stmt.setString(2, autor.getNacionalidade());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void atualizarAutor(Autor autor) {
            String sql = "UPDATE Autor SET Nome = ?, Nacionalidade = ? WHERE ID_Autor = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, autor.getNome());
                stmt.setString(2, autor.getNacionalidade());
                stmt.setInt(3, autor.getIdAutor());
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void excluirAutor(int idAutor) {
            String sql = "DELETE FROM Autor WHERE ID_Autor = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setInt(1, idAutor);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public List<Autor> listarAutores() {
            List<Autor> autores = new ArrayList<>();
            String sql = "SELECT * FROM Autor";
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Autor autor = new Autor(
                            rs.getInt("ID_Autor"),
                            rs.getString("Nome"),
                            rs.getString("Nacionalidade")
                    );
                    autores.add(autor);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return autores;
        }
    }
