package com.projeto.sistema1.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.sistema1.modelos.Projecto;


public interface ProjectoRepositorio extends JpaRepository<Projecto,Long> {
	
	
}
