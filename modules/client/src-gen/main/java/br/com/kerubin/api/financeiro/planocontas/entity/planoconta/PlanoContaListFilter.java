/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:56:10.144
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;


import java.util.HashMap;
import java.util.Map;


public class PlanoContaListFilter {

	private java.util.List<String> codigo;
	
	private java.util.List<String> descricao;
	
	private Boolean ativoIsNotNull;
	
	private Boolean ativoIsNull;
	
	// Map field for developer customizing parameters.
	private Map<Object, Object> customParams = new HashMap<>();
	
	public java.util.List<String> getCodigo() {
		return codigo;
	}
	
	public void setCodigo(java.util.List<String> codigo) {
		this.codigo = codigo;
	}
	
	public java.util.List<String> getDescricao() {
		return descricao;
	}
	
	public void setDescricao(java.util.List<String> descricao) {
		this.descricao = descricao;
	}
	
	public Boolean isAtivoIsNotNull() {
		return ativoIsNotNull != null && ativoIsNotNull;
	}
	
	public Boolean getAtivoIsNotNull() {
		return ativoIsNotNull;
	}
	
	public void setAtivoIsNotNull(Boolean ativoIsNotNull) {
		this.ativoIsNotNull = ativoIsNotNull;
	}
	
	public Boolean isAtivoIsNull() {
		return ativoIsNull != null && ativoIsNull;
	}
	
	public Boolean getAtivoIsNull() {
		return ativoIsNull;
	}
	
	public void setAtivoIsNull(Boolean ativoIsNull) {
		this.ativoIsNull = ativoIsNull;
	}
	
	
	public Map<Object, Object> getCustomParams() {
		return customParams;
	}
	
	public void setCustomParams(Map<Object, Object> customParams) {
		this.customParams = customParams;
	}
	
}
