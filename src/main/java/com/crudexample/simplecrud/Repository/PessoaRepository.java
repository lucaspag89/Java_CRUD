package com.crudexample.simplecrud.Repository;

import com.crudexample.simplecrud.Factory.ConnectionFactory;
import com.crudexample.simplecrud.Model.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaRepository {
    public List<Pessoa> list() {
        List<Pessoa> pessoas = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection()) {

            PreparedStatement prst = conn.prepareStatement("SELECT * FROM pessoa");

            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("estado")
                );

                pessoas.add(pessoa);
            }

        } catch (Exception e) {

        }

        return pessoas;
    }

    public Pessoa getById(int id) {
        Pessoa pessoa = new Pessoa();

        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "SELECT * FROM pessoa WHERE id = ?";

            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setInt(1, id);

            ResultSet rs = prst.executeQuery();

            if (rs.next()){
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setIdade(rs.getInt("idade"));
                pessoa.setEstado(rs.getString("estado"));
            }

        } catch (Exception e) {
            System.out.println("Listagem de alunos FALHOU!");
            e.printStackTrace();
        }

        return pessoa;
    }

    public void create(Pessoa pessoa) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            //Preparar SQL para inserção de dados do aluno.
            String sql = "INSERT INTO pessoa(nome, idade, estado) VALUES(?, ?, ?)";

            //Preparar statement com os parâmetros recebidos
            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setString(1 , pessoa.getNome());
            prst.setInt(2, pessoa.getIdade());
            prst.setString(3 , pessoa.getEstado());

            //Executa inserção e armazena o numero de linhas afetadas
            int rowsAffected = prst.executeUpdate();

            System.out.println("Inserção BEM SUCEDIDA!. Foi adicionada " + rowsAffected + " linha");
            System.out.println("");
        } catch (Exception e) {
            System.out.println("Inserção FALHOU!");
            System.out.println("");
            e.printStackTrace();
            System.out.println("");
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            //Preparar SQL para deletar uma linha.
            String sql = "DELETE FROM pessoa WHERE id = ?";

            //Preparar statement com os parâmetros recebidos
            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setInt(1 , id);

            //Executa delete e armazena o numero de linhas afetadas
            int rowsAffected = prst.executeUpdate();

            System.out.println("Delete BEM SUCEDID0! Foi deletada " + rowsAffected + " linha");
            System.out.println("");
        } catch (Exception e) {
            System.out.println("Delete FALHOU!");
            e.printStackTrace();
            System.out.println("");
        }
    }

    public void update(Pessoa pessoa){
        try (Connection conn = ConnectionFactory.getConnection()) {

            //Preparar SQL para atualizar linhas.
            String sql = "UPDATE pessoa SET nome = ?, idade = ?, estado = ? WHERE id = ?";

            //Preparar statement com os parâmetros recebidos
            PreparedStatement prst = conn.prepareStatement(sql);
            prst.setString(1, pessoa.getNome());
            prst.setInt(2, pessoa.getIdade());
            prst.setString(3, pessoa.getEstado());
            prst.setInt(4, pessoa.getId());

            //Executa atualização e armazena o numero de linhas afetadas
            int rowsAffected = prst.executeUpdate();

            System.out.println("Atualização BEM SUCEDIDA! Foi atualizada: " + rowsAffected + " linha");
        } catch (Exception e) {
            System.out.println("Atualização FALHOU!");
            e.printStackTrace();
        }
    }
}