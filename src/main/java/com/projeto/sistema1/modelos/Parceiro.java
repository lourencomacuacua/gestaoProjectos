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
public class Parceiro implements Serializable{
	private static final long serialVersionUID=1L;
	@Id //referente a geração de id automático
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
	private String nome;
	private String email;
	private String dataCadastro;
	@OneToMany(mappedBy = "parceiro",cascade=CascadeType.REMOVE)
	private List<Contrato> contratos ;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public List<Contrato> getContratos() {
		return contratos;
	}
	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}
	
	

}
