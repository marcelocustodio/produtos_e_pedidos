package com.senior.crudmanytomany.crudmanytomany3;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.senior.crudmanytomany.crudmanytomany3.model.Pedido;
import com.senior.crudmanytomany.crudmanytomany3.model.Produto;
import com.senior.crudmanytomany.crudmanytomany3.model.ProdutoPedido;
import com.senior.crudmanytomany.crudmanytomany3.repository.PedidoRepository;
import com.senior.crudmanytomany.crudmanytomany3.repository.ProdutoPedidoRepository;
import com.senior.crudmanytomany.crudmanytomany3.repository.ProdutoRepository;

@SpringBootApplication
public class Crudmanytomany3Application implements CommandLineRunner {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ProdutoPedidoRepository produtoPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(Crudmanytomany3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// criar dois produtos
		Produto produto1 = new Produto();
		produto1.setTitulo("cadeira plastica");
		produto1.setDescricao("Uma cadeira plástica");
		produto1.setPreco(40.0d);

		Produto produto2 = new Produto();
		produto2.setTitulo("mesa");
		produto2.setDescricao("Artefato para apoiar coisas");
		produto2.setPreco(35.0d);
		
		Produto produto3 = new Produto();
		produto3.setTitulo("amassadeira");
		produto3.setDescricao("whatever");
		produto3.setPreco(13.0d);

		this.produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));

		this.produtoRepository.findByPrecoLessThan(39.0d)
				.forEach(p -> System.out.println("Preços abaixo de R$ 30,00: " + p.getTitulo()));

		Pedido pedido = new Pedido();
		pedido.setCliente("Marcelo Monteiro");
		// salvar pedido
		this.pedidoRepository.save(pedido);

		ProdutoPedido produtoPedido = new ProdutoPedido();
		produtoPedido.setPedido(pedido);
		produtoPedido.setProduto(produto1);
		produtoPedido.setQuantidade(5);
		
		System.out.println(produtoPedido);
		
		//this.produtoPedidoRepository.save(produtoPedido);

		System.out.println("ID do produto: " + produto1.getId());
		System.out.println("ID do pedido: " + pedido.getId());
		
		// atualizar produto
//		produto1.getProdutoPedidos().add(produtoPedido);
//		this.produtoRepository.save(produto1);
//		// atualizar pedido
//		pedido.getProdutoPedidos().add(produtoPedido);
//		this.pedidoRepository.save(pedido);
		

	
		
	};

}
