package com.senior.crudmanytomany.crudmanytomany3.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senior.crudmanytomany.crudmanytomany3.dto.ProdutoPedidoDTO;
import com.senior.crudmanytomany.crudmanytomany3.model.Pedido;
import com.senior.crudmanytomany.crudmanytomany3.model.Produto;
import com.senior.crudmanytomany.crudmanytomany3.model.ProdutoPedido;
import com.senior.crudmanytomany.crudmanytomany3.repository.PedidoRepository;
import com.senior.crudmanytomany.crudmanytomany3.repository.ProdutoPedidoRepository;
import com.senior.crudmanytomany.crudmanytomany3.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/v1")
public class ProdutoPedidoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoPedidoRepository produtoPedidoRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@GetMapping("/vendas")
	public List<Pedido> listarVendas() {
		System.out.println("LISTAGEM DE VENDAS......");
		return (List<Pedido>) pedidoRepository.findAll();
	}

	@GetMapping("/itensdevendas")
	public List<ProdutoPedido> listarItensDeVendas() {
		System.out.println("LISTAGEM DE ITENS DE VENDAS......");
		return (List<ProdutoPedido>) produtoPedidoRepository.findAll();
	}

	@PostMapping("/itensdevendas")
	public ProdutoPedido salvarProdutoPedido(@RequestBody ProdutoPedidoDTO produtoPedidoDTO) {

	    ProdutoPedido prodPedido = new ProdutoPedido();
	    // add values

	    System.out.println("Primeiro: " + produtoPedidoDTO.getProduto());
	    
	    // add Produto obj to ProdutoPedido
	    Produto produto = new Produto();
	    //produto.setId(produtoPedidoDTO.getProduto());
	    produto = produtoRepository.findById(produtoPedidoDTO.getProduto()).get();
	    prodPedido.setProduto(produto);
	    
	    System.out.println("Segundo: " + produtoPedidoDTO.getPedido());

	    // do same for Pedido 
	    Pedido pedido = new Pedido();
	    // pedido.setId(produtoPedidoDTO.getPedido());
	    pedido = pedidoRepository.findById(produtoPedidoDTO.getPedido()).get();
	    prodPedido.setPedido(pedido);
	    
	    prodPedido.setQuantidade(produtoPedidoDTO.getQuantidade());

	    return produtoPedidoRepository.save(prodPedido);
	}

}