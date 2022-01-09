package com.thiagao.desafiotqi.clientes;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.thiagao.desafiotqi.emprestimos.Emprestimo;

@Entity
@Table(name="cliente")
public class Cliente implements UserDetails{
	

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="cliente_sequence", sequenceName="cliente_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cliente_sequence")
	private Long id;
	private String nome;
	private String email;
	private Integer cpf;
	private Integer rg;
	private String endereco;
	private BigDecimal renda;
	private String senha;
	// OFFLINE - todos os usuarios estao sendo cadastrados como USER
	@Enumerated(EnumType.STRING)
	private UserRole user_role = UserRole.USER;
	private Boolean locked = false;
	private Boolean enable = true;
	
	@JsonManagedReference
	@OneToMany(mappedBy="cliente")
	private List<Emprestimo> emprestimos = null;
	
	public Cliente() {
		super();
	}

	//contrutor full
	public Cliente(Long id, String nome, String email, Integer cpf, Integer rg, String endereco, String renda,
			String senha, UserRole user_role, Boolean locked, Boolean enable) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.renda = new BigDecimal(renda);
		this.senha = senha;
		this.user_role = user_role;
		this.locked = locked;
		this.enable = enable;
	}
	
	// construtor sem ID
	public Cliente(String nome, String email, Integer cpf, Integer rg, String endereco, 
			String renda, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.rg = rg;
		this.endereco = endereco;
		this.renda = new BigDecimal(renda);
		this.senha = senha;
	}
	
	// Construtor teste
	public Cliente(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user_role.name());
		return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enable;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public BigDecimal getRenda() {
		return renda;
	}

	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

	public UserRole getUser_role() {
		return user_role;
	}

	public void setUser_role(UserRole user_role) {
		this.user_role = user_role;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	

}

