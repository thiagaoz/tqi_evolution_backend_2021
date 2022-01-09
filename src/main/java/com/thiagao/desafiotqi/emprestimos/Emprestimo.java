package com.thiagao.desafiotqi.emprestimos;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thiagao.desafiotqi.clientes.Cliente;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name="emprestimo")
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Emprestimo {

	@Id
	@SequenceGenerator(name="emprestimo_sequence", sequenceName="emprestimo_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="emprestimo_sequence")
	private Long id;
	private BigDecimal valor;
	private Integer parcelas;
	private LocalDate primeira_parcela;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="cliente")
	private Cliente cliente;
	
	public Emprestimo() {
		super();
	}

	public Emprestimo(Long id, String valor, Integer parcelas, LocalDate primeira_parcela) {
		super();
		this.id = id;
		this.valor = new BigDecimal(valor);
		this.parcelas = parcelas;
		this.primeira_parcela = primeira_parcela;

		
	}

	public Emprestimo(String valor, String parcelas, String primeira_parcela) {
		super();
		this.valor = new BigDecimal(valor);
		this.parcelas = Integer.parseInt(parcelas) ;
		this.primeira_parcela = LocalDate.parse(primeira_parcela) ;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}

	public LocalDate getPrimeira_parcela() {
		return primeira_parcela;
	}

	public void setPrimeira_parcela(LocalDate primeira_parcela) {
		this.primeira_parcela = primeira_parcela;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}
