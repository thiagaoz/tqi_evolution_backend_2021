package com.thiagao.desafiotqi.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
	private final String email;
    private final String senha;
    private final String nome;
    private final Integer cpf;
    private final Integer rg;
    private final String endereco;
    private final String renda;
    
	public RegistrationRequest(String nome, String email, Integer cpf, Integer rg, String endereco, 
			String renda, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.renda = renda;
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public String getNome() {
		return nome;
	}

	public Integer getCpf() {
		return cpf;
	}

	public Integer getRg() {
		return rg;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getRenda() {
		return renda;
	}
	
	
    
    
}
