/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:56:10.144
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;

import br.com.kerubin.api.messaging.core.DomainEvent;
import br.com.kerubin.api.financeiro.planocontas.TipoPlanoContaFinanceiro;
import br.com.kerubin.api.financeiro.planocontas.TipoReceitaDespesa;

public class PlanoContaEvent implements DomainEvent {
	
	public static final String PLANO_CONTA_CREATED = "planoContaCreated";
	public static final String PLANO_CONTA_UPDATED = "planoContaUpdated";
	public static final String PLANO_CONTA_DELETED = "planoContaDeleted";
	private java.util.UUID id;
	
	private String codigo;
	
	private String descricao;
	
	private TipoPlanoContaFinanceiro tipoFinanceiro;
	
	private TipoReceitaDespesa tipoReceitaDespesa;
	
	private java.util.UUID planoContaPai;
	
	private Boolean ativo;
	
	public PlanoContaEvent() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	public PlanoContaEvent(java.util.UUID id, String codigo, String descricao, TipoPlanoContaFinanceiro tipoFinanceiro, TipoReceitaDespesa tipoReceitaDespesa, java.util.UUID planoContaPai, Boolean ativo) {
		this.id = id;
		this.codigo = codigo;
		this.descricao = descricao;
		this.tipoFinanceiro = tipoFinanceiro;
		this.tipoReceitaDespesa = tipoReceitaDespesa;
		this.planoContaPai = planoContaPai;
		this.ativo = ativo;
	}
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public TipoPlanoContaFinanceiro getTipoFinanceiro() {
		return tipoFinanceiro;
	}
	
	public TipoReceitaDespesa getTipoReceitaDespesa() {
		return tipoReceitaDespesa;
	}
	
	public java.util.UUID getPlanoContaPai() {
		return planoContaPai;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setTipoFinanceiro(TipoPlanoContaFinanceiro tipoFinanceiro) {
		this.tipoFinanceiro = tipoFinanceiro;
	}
	
	public void setTipoReceitaDespesa(TipoReceitaDespesa tipoReceitaDespesa) {
		this.tipoReceitaDespesa = tipoReceitaDespesa;
	}
	
	public void setPlanoContaPai(java.util.UUID planoContaPai) {
		this.planoContaPai = planoContaPai;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanoContaEvent other = (PlanoContaEvent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return 31;
	}
	
	/* 
	@Override
	public String toString() {
		// Enabling toString for JPA entities will implicitly trigger lazy loading on all fields.
	}
	*/

}
