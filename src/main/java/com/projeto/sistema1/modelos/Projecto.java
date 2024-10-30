package com.projeto.sistema1.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity //para referenciar que é uma entidade
public class Projecto implements Serializable{
	private static final long serialVersionUID=1L;
	@Id //referente a geração de id automático
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
	private String nome;
	private String descricao;
	@OneToMany(mappedBy = "projecto",cascade=CascadeType.REMOVE)
	private List<Contrato> cargos ;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Contrato> getCargos() {
		return cargos;
	}
	public void setCargos(List<Contrato> cargos) {
		this.cargos = cargos;
	}
	
	
}
