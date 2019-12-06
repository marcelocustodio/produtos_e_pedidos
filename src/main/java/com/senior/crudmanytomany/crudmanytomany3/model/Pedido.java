package com.senior.crudmanytomany.crudmanytomany3.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "pedidos")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cliente;

    @OneToMany(mappedBy = "pedido", cascade = {CascadeType.ALL})
    private Set<ProdutoPedido> produtoPedidos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Set<ProdutoPedido> getProdutoPedidos() {
		return produtoPedidos;
	}

	public void setProdutoPedidos(Set<ProdutoPedido> produtoPedidos) {
		this.produtoPedidos = produtoPedidos;
	}
	
    
}