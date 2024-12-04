import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import entity.*;
import dao.*;

public class Main {

    private static final String DATABASE_URL = "jdbc:sqlite:gerenciamento_obras.db";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            if (connection != null) {
                Statement stmt = connection.createStatement();

                // Criando a tabela Projeto
                String sqlProjeto = "CREATE TABLE IF NOT EXISTS Projeto (" +
                        "ID_Projeto INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Nome_Projeto TEXT NOT NULL, " +
                        "Local TEXT NOT NULL, " +
                        "Data_Inicio DATE NOT NULL, " +
                        "Data_Termino DATE NOT NULL)";
                stmt.execute(sqlProjeto);

                // Criando a tabela Engenheiro
                String sqlEngenheiro = "CREATE TABLE IF NOT EXISTS Engenheiro (" +
                        "ID_Engenheiro INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Nome_Engenheiro TEXT NOT NULL, " +
                        "Especialidade TEXT NOT NULL)";
                stmt.execute(sqlEngenheiro);

                // Criando a tabela Operario
                String sqlOperario = "CREATE TABLE IF NOT EXISTS Operario (" +
                        "ID_Operario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Nome_Operario TEXT NOT NULL, " +
                        "Funcao TEXT NOT NULL)";
                stmt.execute(sqlOperario);

                // Criando a tabela Equipamento
                String sqlEquipamento = "CREATE TABLE IF NOT EXISTS Equipamento (" +
                        "ID_Equipamento INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Nome_Equipamento TEXT NOT NULL, " +
                        "Tipo TEXT NOT NULL)";
                stmt.execute(sqlEquipamento);

                // Criando a tabela Material
                String sqlMaterial = "CREATE TABLE IF NOT EXISTS Material (" +
                        "ID_Material INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Nome_Material TEXT NOT NULL, " +
                        "Quantidade INTEGER NOT NULL)";
                stmt.execute(sqlMaterial);

                // Criando a tabela Alocacao_Engenheiro
                String sqlAlocacaoEngenheiro = "CREATE TABLE IF NOT EXISTS Alocacao_Engenheiro (" +
                        "ID_Projeto INTEGER NOT NULL, " +
                        "ID_Engenheiro INTEGER NOT NULL, " +
                        "PRIMARY KEY (ID_Projeto, ID_Engenheiro), " +
                        "FOREIGN KEY (ID_Projeto) REFERENCES Projeto(ID_Projeto), " +
                        "FOREIGN KEY (ID_Engenheiro) REFERENCES Engenheiro(ID_Engenheiro))";
                stmt.execute(sqlAlocacaoEngenheiro);

                // Criando a tabela Alocacao_Operario
                String sqlAlocacaoOperario = "CREATE TABLE IF NOT EXISTS Alocacao_Operario (" +
                        "ID_Projeto INTEGER NOT NULL, " +
                        "ID_Operario INTEGER NOT NULL, " +
                        "PRIMARY KEY (ID_Projeto, ID_Operario), " +
                        "FOREIGN KEY (ID_Projeto) REFERENCES Projeto(ID_Projeto), " +
                        "FOREIGN KEY (ID_Operario) REFERENCES Operario(ID_Operario))";
                stmt.execute(sqlAlocacaoOperario);

                // Criando a tabela Uso_Equipamento
                String sqlUsoEquipamento = "CREATE TABLE IF NOT EXISTS Uso_Equipamento (" +
                        "ID_Projeto INTEGER NOT NULL, " +
                        "ID_Equipamento INTEGER NOT NULL, " +
                        "PRIMARY KEY (ID_Projeto, ID_Equipamento), " +
                        "FOREIGN KEY (ID_Projeto) REFERENCES Projeto(ID_Projeto), " +
                        "FOREIGN KEY (ID_Equipamento) REFERENCES Equipamento(ID_Equipamento))";
                stmt.execute(sqlUsoEquipamento);

                // Criando a tabela Consumo_Material
                String sqlConsumoMaterial = "CREATE TABLE IF NOT EXISTS Consumo_Material (" +
                        "ID_Projeto INTEGER NOT NULL, " +
                        "ID_Material INTEGER NOT NULL, " +
                        "PRIMARY KEY (ID_Projeto, ID_Material), " +
                        "FOREIGN KEY (ID_Projeto) REFERENCES Projeto(ID_Projeto), " +
                        "FOREIGN KEY (ID_Material) REFERENCES Material(ID_Material))";
                stmt.execute(sqlConsumoMaterial);

                System.out.println("Tabelas criadas com sucesso!");

                ProjetoDAO projetoDAO = new ProjetoDAO();
                EngenheiroDAO engenheiroDAO = new EngenheiroDAO();
                OperarioDAO operarioDAO = new OperarioDAO();
                EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
                MaterialDAO materialDAO = new MaterialDAO();


                // Inserindo Projeto
                Projeto projeto = new Projeto();
                projeto.setNomeProjeto("Construção do Prédio A");
                projeto.setLocal("São Paulo");
                projeto.setDataInicio(java.sql.Date.valueOf("2024-01-01"));
                projeto.setDataTermino(java.sql.Date.valueOf("2024-12-31"));
                projetoDAO.inserirProjeto(projeto);

                // Inserindo Engenheiros
                Engenheiro engenheiro1 = new Engenheiro();
                engenheiro1.setNomeEngenheiro("Carlos Silva");
                engenheiro1.setEspecialidade("Estruturas");
                engenheiroDAO.inserirEngenheiro(engenheiro1);

                Engenheiro engenheiro2 = new Engenheiro();
                engenheiro2.setNomeEngenheiro("Ana Oliveira");
                engenheiro2.setEspecialidade("Geotécnica");
                engenheiroDAO.inserirEngenheiro(engenheiro2);

                // Inserindo Operários
                Operario operario1 = new Operario();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabelas: " + e.getMessage());
        }
    }
}
