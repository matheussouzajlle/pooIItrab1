import dao.*;
import entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        // Obtendo a conexão com o banco de dados
        Connection connection = ConexaoBD.getInstance().getConnection();

        // Criando as tabelas, se não existirem
        criarTabelas(connection);

        // Exemplo de uso dos DAOs
        AutorDAO autorDAO = new AutorDAO();
        LivroDAO livroDAO = new LivroDAO();

        // Aqui você pode inserir, atualizar, excluir ou listar autores e livros


        // Inserindo livros para o autor com ID 1
        Livro livro1 = new Livro();
        livro1.setTitulo("vida");
        livro1.setAnoPublicacao(2024);
        livro1.setIdAutor(1); // Supondo que o ID do autor é 1
        livroDAO.inserirLivro(livro1);

        Livro livro2 = new Livro();
        livro2.setTitulo("animal");
        livro2.setAnoPublicacao(2025);
        livro2.setIdAutor(1); // Supondo que o ID do autor é 1
        livroDAO.inserirLivro(livro2);

        // Inserindo livros para o autor com ID 2
        Livro livro3 = new Livro();
        livro3.setTitulo("Livro dos Sonhos");
        livro3.setAnoPublicacao(2024);
        livro3.setIdAutor(2); // Supondo que o ID do autor é 2
        livroDAO.inserirLivro(livro3);

        Livro livro4 = new Livro();
        livro4.setTitulo("Mistérios da Terra");
        livro4.setAnoPublicacao(2025);
        livro4.setIdAutor(2); // Supondo que o ID do autor é 2
        livroDAO.inserirLivro(livro4);
    }

    private static void criarTabelas(Connection connection) {
        String createAutorTable = "CREATE TABLE IF NOT EXISTS Autor (" +
                "ID_Autor INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nome TEXT NOT NULL, " +
                "Nacionalidade TEXT)";

        String createLivroTable = "CREATE TABLE IF NOT EXISTS Livro (" +
                "ID_Livro INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Titulo TEXT NOT NULL, " +
                "Ano_Publicacao INTEGER, " +
                "ID_Autor INTEGER, " +
                "FOREIGN KEY(ID_Autor) REFERENCES Autor(ID_Autor))";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createAutorTable);
            stmt.execute(createLivroTable);
            System.out.println("Tabelas criadas com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        AutorDAO autorDAO = new AutorDAO();
        /*
        // Criando um novo autor
        Autor autor1 = new Autor();
        autor1.setNome("Jorge Amado");
        autor1.setNacionalidade("Brasileiro");

        // Inserindo o autor no banco de dados
        autorDAO.inserirAutor(autor1);

        // Criando outro autor
        Autor autor2 = new Autor();
        autor2.setNome("Gabriel Garcia Marquez");
        autor2.setNacionalidade("Colombiano");

        // Inserindo o autor no banco de dados
        autorDAO.inserirAutor(autor2);

        System.out.println("Autores inseridos com sucesso.");

     */
        autorDAO.excluirAutor(2);
        autorDAO.excluirAutor(3);


        // Inserindo os livros


        System.out.println("Livros inseridos com sucesso.");

    }
}
