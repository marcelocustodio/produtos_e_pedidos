package com.senior.crudmanytomany.crudmanytomany3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senior.crudmanytomany.crudmanytomany3.dto.ProdutoDTO;
import com.senior.crudmanytomany.crudmanytomany3.model.Produto;
import com.senior.crudmanytomany.crudmanytomany3.model.ProdutoPedido;
import com.senior.crudmanytomany.crudmanytomany3.repository.ProdutoPedidoRepository;
import com.senior.crudmanytomany.crudmanytomany3.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/v1")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ProdutoPedidoRepository produtoPedidoRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@GetMapping("/produtos")
	public List<Produto> listarProdutos() {
		System.out.println("LISTAGEM DE PRODUTOS......");

//		QProduto customer = QProduto.customer;
//		JPAQuery<?> query = new JPAQuery<Void>(entityManager);
//		Customer bob = query.select(customer)
//		  .from(customer)
//		  .where(customer.firstName.eq("Bob"))
//		  .fetchOne();

//		JPAQuery query = new JPAQuery(entityManager);
//		QPRoduto produto = QProduto;
		// return query.from(person).where(person.firstname.eq(firstname)).list(person);

		return (List<Produto>) produtoRepository.findAll();
	}

	@GetMapping("/produtos/{uuid}")
	public Produto procurarProduto(@PathVariable(value = "uuid") String uuid) {
		//
		Produto produto = produtoRepository.findByuuid(uuid);

		return produto;
	}

	@GetMapping("/produtos/listarMesas")
	public List<Produto> listarMesas() {
		System.out.println("LISTAGEM DE MESAS......");

		return (List<Produto>) produtoRepository.findByTituloContaining("mesa");
	}

	@GetMapping("/produtos/abaixoDe/{preco}")
	public List<Produto> listarPrecosAbaixoDe(@PathVariable(value = "preco") double preco) {
		System.out.println("LISTAGEM DE PREÇOS ABAIXO DE......");

		return (List<Produto>) produtoRepository.findByPrecoLessThan(preco);
	}

	@PostMapping("/produtos")
	public Produto salvarProduto(@RequestBody ProdutoDTO produtoDTO) {

		Produto produto = new Produto();

		UUID uuid = UUID.randomUUID();
		String uuidGerado = uuid.toString();
		System.out.println("UUID gerado: " + uuidGerado);

		produto.setUuid(uuidGerado);
		produto.setEstoque(produtoDTO.getEstoque());
		produto.setPreco(produtoDTO.getPreco());
		produto.setTitulo(produtoDTO.getTitulo());
		produto.setTipo(produtoDTO.getTipo());
		produto.setAtivo(true);

		return produtoRepository.save(produto);
	}

	@PutMapping("/produtos/{uuid}")
	public Produto atualizarProduto(@PathVariable(value = "uuid") String uuid, @RequestBody Produto produtoAtualizado) {

		Produto produto = produtoRepository.findByuuid(uuid);
		produto.setAtivo(produtoAtualizado.isAtivo());
		produto.setPreco(produtoAtualizado.getPreco());
		produto.setTitulo(produtoAtualizado.getTitulo());
		produto.setEstoque(produtoAtualizado.getEstoque());
		produto.setTipo(produtoAtualizado.getTipo());

		return produtoRepository.save(produto);

	}

	@GetMapping("/produtos/desativar/{uuid}")
	public Produto desativarProduto(@PathVariable(value = "uuid") String uuid) {

		Produto produto = produtoRepository.findByuuid(uuid);
		produto.setAtivo(false);

		return produtoRepository.save(produto);

	}

	@DeleteMapping("/produtos/{uuid}")
	public Map<String, Boolean> excluirProduto(@PathVariable(value = "uuid") String uuid) {

		Produto produto = produtoRepository.findByuuid(uuid);
		Map<String, Boolean> resposta = new HashMap<>();

		ProdutoPedido produtoPedido = produtoPedidoRepository.findByProduto_id(produto.getId());
		
		if (produtoPedido != null ) {
			System.out.println("Não dá pra excluir: " + produtoPedido.getProduto().getTitulo());
			resposta.put("produtoExcluido", Boolean.FALSE);
		} else {
			produtoRepository.delete(produto);
			resposta.put("produtoExcluido", Boolean.TRUE);
		}

		return resposta;
	}

}