package com.projeto.sistema1.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.sistema1.modelos.Parceiro;


public interface ParceiroRepositorio extends JpaRepository<Parceiro,Long> {
	
	
}
