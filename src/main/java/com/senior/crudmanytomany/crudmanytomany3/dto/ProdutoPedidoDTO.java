package com.senior.crudmanytomany.crudmanytomany3.dto;

public class ProdutoPedidoDTO {

//    private Long id;
    private Long produto;
    private Long pedido;
    private int quantidade;
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	public Long getProduto() {
		return produto;
	}
	public void setProduto(Long produto) {
		this.produto = produto;
	}
	public Long getPedido() {
		return pedido;
	}
	public void setPedido(Long pedido) {
		this.pedido = pedido;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
    
}