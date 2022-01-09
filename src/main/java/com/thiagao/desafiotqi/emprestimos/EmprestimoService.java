package com.thiagao.desafiotqi.emprestimos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagao.desafiotqi.clientes.Cliente;

@Service
public class EmprestimoService {

	private EmprestimoRepository emprestimoRepository;
	
	@Autowired
	public EmprestimoService(EmprestimoRepository emprestimoRepository) {
		super();
		this.emprestimoRepository = emprestimoRepository;
	}
	
	
	public List<Emprestimo> getEmprestimos(){
		return emprestimoRepository.findAll();
	}
	
	// Tenta fazer um pedido de empréstimo 
	public String pedirEmprestimo(Cliente cliente, Emprestimo emprestimo) {
		// testa se valor do empréstimo é menor 1, ou campo está vazio
		if (emprestimo.getValor().compareTo(new BigDecimal(1)) < 0 || emprestimo.getValor() == null) {
			throw new IllegalStateException("Valor muito baixo");
		}
		// testa se o número de parcelas é maior que 60
		if (emprestimo.getParcelas() > 60) {
			throw new IllegalStateException("Número de parcelas excedido. Máximo é 60x");
		}
		
		// testa se a data da primeira parcela é depois de 3 meses, contando do dia atual
		if (emprestimo.getPrimeira_parcela().isAfter(LocalDate.now().plusMonths(3))) {
			throw new IllegalStateException("Primeira parcela muito distante. Máximo é de 3 meses contando de hoje");
		}
		
		emprestimo.setCliente(cliente);
		emprestimoRepository.save(emprestimo);
		return "Empréstimo Efetuado";
	}
	
	//OFFLINE
	public String[] detalheEmprestimo(Emprestimo emprestimo) {
		Cliente cliente = emprestimo.getCliente();
		return new String[] {emprestimo.getId().toString(), emprestimo.getValor().toString(), emprestimo.getParcelas().toString(),
				emprestimo.getPrimeira_parcela().toString(), cliente.getEmail(), cliente.getRenda().toString()};
		}

}
