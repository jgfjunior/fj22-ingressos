package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Entity;
import org.springframework.data.annotation.Id;

import br.com.caelum.ingresso.model.descontos.Desconto;

@Entity
public class Ingresso {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Sessao sessao;
	
	@ManyToOne
	private Lugar lugar;
	
	private BigDecimal preco;
	
	@Enumerated(EnumType.STRING)
	private TipoDeIngresso tipoDeIngresso;

	public Ingresso(Sessao sessao, TipoDeIngresso tipoDeDesconto, Lugar lugar) {
		this.sessao = sessao;
		this.tipoDeIngresso = tipoDeDesconto;
		this.preco = this.tipoDeIngresso.aplicaDesconto(sessao.getPreco());
		this.lugar = lugar;
	}
	
	public Ingresso(Sessao sessao, Desconto tipoDeDesconto){
		this.setSessao(sessao);
		this.setPreco(tipoDeDesconto.aplicarDescontoSobre(sessao.getPreco()));
		
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public Ingresso(Sessao sessao, Desconto tipoDeDesconto, Lugar lugar) {
		this.sessao = sessao;
		this.preco = tipoDeDesconto.aplicarDescontoSobre(sessao.getPreco());
		this.setLugar(lugar);
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

}
