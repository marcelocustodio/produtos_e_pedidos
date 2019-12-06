package com.senior.crudmanytomany.crudmanytomany3.repository;

import org.springframework.data.repository.CrudRepository;

import com.senior.crudmanytomany.crudmanytomany3.model.ProdutoPedido;

public interface ProdutoPedidoRepository extends CrudRepository<ProdutoPedido, Long> {

//    List<Produto> findByTituloContaining(String titulo);

//    List<Produto> findByPrecoLessThan(double preco);
}