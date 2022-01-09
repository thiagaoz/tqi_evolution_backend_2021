package com.thiagao.desafiotqi.registration;

import org.springframework.stereotype.Service;

import com.thiagao.desafiotqi.clientes.Cliente;
import com.thiagao.desafiotqi.clientes.ClienteService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {

	private final ClienteService clienteService;
	private final EmailValidator emailValidator;
	
	public RegistrationService(ClienteService clienteService, EmailValidator emailValidator) {
		super();
		this.clienteService = clienteService;
		this.emailValidator = emailValidator;
	}

	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		if(!isValidEmail){
			throw new IllegalStateException("Email não é válido");
		}
		return clienteService.signUpCliente(
				new Cliente(
						request.getNome(),
						request.getEmail(),
						request.getCpf(),
						request.getRg(),
						request.getEndereco(),
						request.getRenda(),
						request.getSenha()
						)
				);
	}

}
