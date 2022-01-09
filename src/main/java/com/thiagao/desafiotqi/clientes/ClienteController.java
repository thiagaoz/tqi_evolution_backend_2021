package com.thiagao.desafiotqi.clientes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagao.desafiotqi.emprestimos.Emprestimo;
import com.thiagao.desafiotqi.emprestimos.EmprestimoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path="cliente")
public class ClienteController {
	
	private final ClienteService clienteService;
	private final EmprestimoService emprestimoService;
	
	@Autowired
	public ClienteController(ClienteService clienteService, EmprestimoService emprestimoService) {
		super();
		this.clienteService = clienteService;
		this.emprestimoService = emprestimoService;
	}
	
	// TESTE - lista todos clientes cadastrados
	@GetMapping(path="all")
	public List<Cliente> getClientes(){
		return clienteService.getClientes();
	}
	
	// lista todos os emprestimos do cliente logado
	@GetMapping(path="emprestimos")
	public List<Emprestimo> getEmprestimos(@AuthenticationPrincipal Cliente cliente){
		return clienteService.getEmprestimos(cliente);
	}
	
	// Perfil do cliente, acessa todas as informações dele
	@GetMapping(path="perfil")
	public Cliente getPerfil(@AuthenticationPrincipal Cliente cliente){
		return clienteService.getPerfil(cliente);
	}
	
	// Cliente logado faz um pedido de empréstimo
	@PostMapping(path="pedir_emprestimo")
	public String pedirEmprestimo(@AuthenticationPrincipal Cliente cliente, @RequestBody Emprestimo emprestimo ) {
		return emprestimoService.pedirEmprestimo(cliente, emprestimo);
		  
	}
}
