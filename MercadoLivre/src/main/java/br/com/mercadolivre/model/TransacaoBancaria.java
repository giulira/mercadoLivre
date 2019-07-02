package br.com.mercadolivre.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TransacaoBancaria implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String cpfOrigem;
	private String cpfDestino;
	private BigDecimal valor;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCpfOrigem() {
		return cpfOrigem;
	}
	public void setCpfOrigem(String cpfOrigem) {
		this.cpfOrigem = cpfOrigem;
	}
	public String getCpfDestino() {
		return cpfDestino;
	}
	public void setCpfDestino(String cpfDestino) {
		this.cpfDestino = cpfDestino;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
}
