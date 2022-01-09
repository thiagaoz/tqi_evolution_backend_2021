package com.thiagao.desafiotqi.clientes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.thiagao.desafiotqi.emprestimos.Emprestimo;

@Service
public class ClienteService implements UserDetailsService{
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final static String EMAIL_NAO_CADASTRADO = "O email %s não está cadastrado";
	private ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository clienteRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.clienteRepository = clienteRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	//Adiciona novo cliente, testando se já existe o email cadastrado
	public void addCliente(Cliente cliente) {
		Optional<Cliente> clienteOptional = clienteRepository.findByEmail(cliente.getEmail());
		if(clienteOptional.isPresent()) {
			throw new IllegalStateException("Email já cadastrado");
		}
		
		clienteRepository.save(cliente);
	}

	// OFFLINE - lista todos os clientes clientes cadastrados
	public List<Cliente> getClientes(){
		return clienteRepository.findAll();
	}

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return clienteRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
				String.format(EMAIL_NAO_CADASTRADO, email)));
	}

	//Cadastra cliente
	public String signUpCliente(Cliente cliente){
		
		boolean clienteExists = clienteRepository.findByEmail(cliente.getEmail()).isPresent();

		if (clienteExists){
			throw new IllegalStateException("Email já cadastrado");
		}
		
		String encodedPassword = bCryptPasswordEncoder.encode(cliente.getSenha());

		cliente.setSenha(encodedPassword);
		
		clienteRepository.save(cliente);
		
		return "FUNFOUN 2";
	}
	
	public Cliente getPerfil(Cliente cliente) {
		return clienteRepository.getByEmail(cliente.getEmail());
	}

	public List<Emprestimo> getEmprestimos(Cliente cliente) {
		return clienteRepository.getByEmail(cliente.getEmail()).getEmprestimos();
	}
}
