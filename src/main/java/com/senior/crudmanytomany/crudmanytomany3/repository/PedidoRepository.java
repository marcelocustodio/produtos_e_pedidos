package com.senior.crudmanytomany.crudmanytomany3.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.senior.crudmanytomany.crudmanytomany3.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long> {

	List<Pedido> findByValorTotalLessThan(double valorTotal);
	
	Pedido findByuuid(String uuid);
	
}