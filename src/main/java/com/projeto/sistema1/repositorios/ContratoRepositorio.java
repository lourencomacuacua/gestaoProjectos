package com.projeto.sistema1.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.sistema1.modelos.Contrato;

public interface ContratoRepositorio extends JpaRepository<Contrato,Long> {
	List<Contrato> findByDataExpiracao(LocalDate dataExpiracao);
	
}
