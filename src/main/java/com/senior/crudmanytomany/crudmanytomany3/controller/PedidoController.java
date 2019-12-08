package com.senior.crudmanytomany.crudmanytomany3.controller;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senior.crudmanytomany.crudmanytomany3.dto.PedidoDTO;
import com.senior.crudmanytomany.crudmanytomany3.dto.VendaDTO;
import com.senior.crudmanytomany.crudmanytomany3.model.Pedido;
import com.senior.crudmanytomany.crudmanytomany3.repository.PedidoRepository;
import com.senior.crudmanytomany.crudmanytomany3.repository.ProdutoPedidoRepository;
import com.senior.crudmanytomany.crudmanytomany3.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/v1")
public class PedidoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoPedidoRepository produtoPedidoRepository;

	@PersistenceContext
	private EntityManager entityManager;
	
	@GetMapping("/pedidos")
	public List<Pedido> listarPedidos() {
		
		System.out.println("LISTAGEM DE PEDIDOS ......");
		
		return (List<Pedido>) pedidoRepository.findAll();
	}
	
	@GetMapping("/pedidos/{uuid}")
	public Pedido procurarPedido(@PathVariable(value = "uuid") String uuid) {
		
		Pedido pedido = pedidoRepository.findByuuid(uuid);

		return pedido;
	}
	
	@GetMapping("/pedidos/valorTotalAbaixoDe/{preco}")
	public List<Pedido> listarValorTotalAbaixoDe(@PathVariable(value = "valorTotal") double valorTotal) {
		return (List<Pedido>) pedidoRepository.findByValorTotalLessThan(valorTotal);
	}
	
	@PostMapping("/pedidos")
	public Pedido salvarPedido(@RequestBody PedidoDTO pedidoDTO) {

		Pedido pedido = new Pedido();

		UUID uuid = UUID.randomUUID();
		String uuidGerado = uuid.toString();
		System.out.println("UUID gerado: " + uuidGerado);

		pedido.setUuid(uuidGerado);
		pedido.setCliente(pedidoDTO.getCliente());
		pedido.setSituacao("aberto");

		return pedidoRepository.save(pedido);
	}
	
	@PostMapping("/realizarVenda")
	public Pedido realizarVenda(@RequestBody VendaDTO vendaDTO) {
		//
		return null;
	}
	
	
/*
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

	@PostMapping("/produtos")
	public Produto salvarProduto(@RequestBody Produto produto) {
		//System.out.println("___ Empregado a salvar: " + employee.toString());
		return produtoRepository.save(produto);
	}
	/*
	@PostMapping("/itensdevendas")
	public ProdutoPedido salvarProdutoPedido(@RequestBody ProdutoPedido produtoPedido) {
		//System.out.println("___ Empregado a salvar: " + employee.toString());
		return produtoPedidoRepository.save(produtoPedido);
	}
	*/
	/*@PostMapping("/itensdevendas")
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

	/*
	 * @GetMapping("/employees/{id}") public ResponseEntity<Employee>
	 * getEmployeeById(@PathVariable(value = "id") Long employeeId) throws
	 * ResourceNotFoundException { Employee employee =
	 * employeeRepository.findById(employeeId) .orElseThrow(() -> new
	 * ResourceNotFoundException("Employee not found for this id :: " +
	 * employeeId)); return ResponseEntity.ok().body(employee); }
	 * 
	 * @PostMapping("/employees") public Employee createEmployee(@RequestBody
	 * Employee employee) { System.out.println("___ Empregado a salvar: " +
	 * employee.toString()); return employeeRepository.save(employee); }
	 * 
	 * @PutMapping("/employees/{id}") public ResponseEntity<Employee>
	 * updateEmployee(@PathVariable(value = "id") Long employeeId,
	 * 
	 * @Valid @RequestBody Employee employeeDetails) throws
	 * ResourceNotFoundException { Employee employee =
	 * employeeRepository.findById(employeeId) .orElseThrow(() -> new
	 * ResourceNotFoundException("Employee not found for this id :: " +
	 * employeeId));
	 * 
	 * employee.setEmailId(employeeDetails.getEmailId());
	 * employee.setLastName(employeeDetails.getLastName());
	 * employee.setFirstName(employeeDetails.getFirstName()); final Employee
	 * updatedEmployee = employeeRepository.save(employee); return
	 * ResponseEntity.ok(updatedEmployee); }
	 * 
	 * @DeleteMapping("/employees/{id}") public Map<String, Boolean>
	 * deleteEmployee(@PathVariable(value = "id") Long employeeId) throws
	 * ResourceNotFoundException { Employee employee =
	 * employeeRepository.findById(employeeId) .orElseThrow(() -> new
	 * ResourceNotFoundException("Employee not found for this id :: " +
	 * employeeId));
	 * 
	 * employeeRepository.delete(employee); Map<String, Boolean> response = new
	 * HashMap<>(); response.put("deleted", Boolean.TRUE); return response; }
	 */

}