package com.thiagao.desafiotqi.clientes;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Optional<Cliente> findByEmail(String email);
	Cliente getByEmail(String email);
}
