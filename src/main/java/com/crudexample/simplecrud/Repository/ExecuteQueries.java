package com.crudexample.simplecrud.Repository;

import com.crudexample.simplecrud.Model.Pessoa;

import java.util.List;

public class ExecuteQueries {
    public static void main(String[] args){
        PessoaRepository pessoaRepository = new PessoaRepository();

        /**
         * Consulta
         */
        List<Pessoa> pessoas = pessoaRepository.list();

        System.out.println("Consulta");
        pessoas.stream().forEach(System.out::println);
        System.out.println("");

        /**
         * Consulta com filtro
         */
        Pessoa pessoa = pessoaRepository.getById(2);

        System.out.println("Consulta com filtro");
        System.out.println(pessoa);
        System.out.println("");

        /**
         * Inserção
         */
        Pessoa insertPessoa = new Pessoa(
                "Matheus",
                43,
                "SP"
        );

//        pessoaRepository.create(insertPessoa);

        /**
         * Deleta
         */
//        pessoaRepository.delete(4);

        /**
         * Atualiza
         */
        Pessoa updatePessoa = pessoaRepository.getById(3);
        updatePessoa.setNome("Joaquim");
        updatePessoa.setIdade(18);
        updatePessoa.setEstado("RS");

//        pessoaRepository.update(updatePessoa);
    }
}
