package dao;

import entity.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivroDAO  extends BaseDAO {

    public void inserirLivro(Livro livro){
        // try-with-resources
        // fecha automaticamente os recursos
        String sql = """
                insert into livro(idLivro,titulo,anoPublicacao,idAutor)
                values (?, ?, ?, ?);
                """;
        try(Connection c = con();
            PreparedStatement pre = c.prepareStatement(sql)){
            // deu erro
            pre.setInt(1,livro.getIdLivro());
            pre.setString(2,livro.getTitulo());
            pre.setDouble(3,livro.getAnoPublicacao());
            pre.setInt(4,livro.getIdAutor());
            pre.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void atualizar(Livro livro){
        // try-with-resources
        // fecha automaticamente os recursos
        String sql = """
                update pessoa set titulo = ?, anoPublicacao = ?,idAutor = ? 
                where idLivro = ? ;
                """;
        try(Connection c = con();
            PreparedStatement pre = c.prepareStatement(sql)){
            // deu erro
            pre.setInt(1,livro.getIdLivro());
            pre.setString(2,livro.getTitulo());
            pre.setDouble(3,livro.getAnoPublicacao());
            pre.setInt(4,livro.getIdAutor());
            pre.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void deletar(Livro livro){
        // try-with-resources
        // fecha automaticamente os recursos
        String sql = """
                delete from livro where idLivro = ? AND idAutor = ? ;
                """;
        try(Connection c = con();
            PreparedStatement pre = c.prepareStatement(sql)){
            // deu erro
            pre.setInt(1,livro.getIdLivro());
            pre.setInt(2,livro.getIdAutor());
            pre.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Livro> obterTodos(){
        List<Livro> lista = new ArrayList<>();
        String sql = """
                select idLivro
                        ,titulo
                        ,anoPublicacao
                        ,idAutor
                 from livro
            order by titulo desc 
                """;
        try(Connection c = con();
            PreparedStatement pre = c.prepareStatement(sql)){
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                Livro l = new Livro();
                l.setIdLivro(rs.getInt("idLivro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAnoPublicacao(rs.getInt("anoPublicacao"));
                l.setIdAutor(rs.getInt("idAutor"));
                lista.add(l);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public Optional<Livro> obterLivroPeloAutor(int idAutor){
        Livro l = null;
        String sql = """
                select  idLivro
                        ,titulo
                        ,anoPublicacao
                        ,idAutor
                 from livro
                 where idAutor = ?;
            order by titulo desc 
                """;
        try(Connection c = con();
            PreparedStatement pre = c.prepareStatement(sql)){
            pre.setInt(1,idAutor);
            ResultSet rs = pre.executeQuery();
            if(rs.next()){
                l = new Livro();
                l.setIdLivro(rs.getInt("idLivro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAnoPublicacao(rs.getInt("anoPublicacao"));
                l.setIdAutor(rs.getInt("idAutor"));
                return Optional.of(l);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
