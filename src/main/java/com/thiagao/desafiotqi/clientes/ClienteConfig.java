package com.thiagao.desafiotqi.clientes;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thiagao.desafiotqi.emprestimos.Emprestimo;
import com.thiagao.desafiotqi.emprestimos.EmprestimoRepository;

@Configuration
public class ClienteConfig {
	
	@Bean
	CommandLineRunner commandLineRunner(ClienteRepository clienteRepository, EmprestimoRepository emprestimoRepository) {
		return args -> {
			// Criando os clientes
			Cliente fulano = new Cliente(
					1L,
					"Fulano da Silva",
					"fulano@gmail.com",
					123456789,
					123456,
					"Av. Brasil, número 1000",
					"3500.90",
					"1234",
					UserRole.USER,
					false,
					true);
			
			//Cliente thiago = new Cliente("thiago@gmail.com", "123456", UserRole.ADMIN);
			
			//Criando os emprestimos
			Emprestimo emp1 = new Emprestimo(
					1L,
					"20000",
					12,
					LocalDate.of(2022, Month.JANUARY, 10)
					);
			
			//Salvando em seus repositorios
			//clienteRepository.saveAll(List.of(fulano, thiago));
			emp1.setCliente(fulano);
			//clienteRepository.saveAll(List.of(fulano));
			//emprestimoRepository.save(emp1);
		};
	} 

}
