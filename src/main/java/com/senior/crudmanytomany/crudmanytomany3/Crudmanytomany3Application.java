package com.senior.crudmanytomany.crudmanytomany3;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

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

	public String gerarUUID() {
		/*
		UUID uuid = UUID.randomUUID();
		MessageDigest salt = null;
		try {
			salt = MessageDigest.getInstance("SHA-256");
			salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String uuidGerado = new String(salt.digest());
		*/
		UUID uuid = UUID.randomUUID();
		String uuidGerado = uuid.toString();
		
		return uuidGerado;
	}
	
	@Override
	public void run(String... args) throws Exception {

		// criar dois produtos
		Produto produto1 = new Produto();
		produto1.setTitulo("cadeira plastica");
		produto1.setPreco(40.0d);
		produto1.setEstoque(487d);
		produto1.setAtivo(true);
		produto1.setUuid(gerarUUID());

		Produto produto2 = new Produto();
		produto2.setTitulo("mesa");
		produto2.setPreco(35.0d);
		produto2.setEstoque(234d);
		produto2.setAtivo(true);
		produto2.setUuid(gerarUUID());
		
		Produto produto3 = new Produto();
		produto3.setTitulo("mesa madeira");
		produto3.setPreco(73.0d);
		produto3.setEstoque(34d);
		produto3.setAtivo(true);
		produto3.setUuid(gerarUUID());
		
		Produto produto4 = new Produto();
		produto4.setTitulo("ferro de passar");
		produto4.setPreco(95.2d);
		produto4.setEstoque(300d);
		produto4.setAtivo(true);
		produto4.setUuid(gerarUUID());

		this.produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4));

		this.produtoRepository.findByPrecoLessThan(39.0d)
				.forEach(p -> System.out.println("Preços abaixo de R$ 39,00: " + p.getTitulo()));

		Pedido pedido = new Pedido();
		pedido.setCliente("Marcelo Monteiro");
		UUID uuid = UUID.randomUUID();
		String uuidGerado = uuid.toString();
		pedido.setUuid(uuidGerado);
		pedido.setSituacao("aberto");
		// salvar pedido
		this.pedidoRepository.save(pedido);

		ProdutoPedido produtoPedido = new ProdutoPedido();
		produtoPedido.setPedido(pedido);
		produtoPedido.setProduto(produto1);
		produtoPedido.setQuantidade(5);
		
		System.out.println(produtoPedido);
		
		//this.produtoPedidoRepository.save(produtoPedido);

//		System.out.println("ID do produto: " + produto1.getId());
//		System.out.println("ID do pedido: " + pedido.getId());
		
		// atualizar produto
//		produto1.getProdutoPedidos().add(produtoPedido);
//		this.produtoRepository.save(produto1);
//		// atualizar pedido
//		pedido.getProdutoPedidos().add(produtoPedido);
//		this.pedidoRepository.save(pedido);
		
	};

}
