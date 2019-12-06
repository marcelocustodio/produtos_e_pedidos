package com.senior.crudmanytomany.crudmanytomany3.repository;

import org.springframework.data.repository.CrudRepository;

import com.senior.crudmanytomany.crudmanytomany3.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

    // List<Produto> findByTituloContaining(String titulo);

    // List<Produto> findByPrecoLessThan(double preco);
}