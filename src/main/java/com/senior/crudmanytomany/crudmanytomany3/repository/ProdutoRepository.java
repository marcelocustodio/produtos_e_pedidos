package com.senior.crudmanytomany.crudmanytomany3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.senior.crudmanytomany.crudmanytomany3.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    List<Produto> findByTituloContaining(String titulo);

    List<Produto> findByPrecoLessThan(double preco);
    
    Produto findByuuid(String uuid);
    
}