package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;


public class PlanoContaListFilter {

	private java.util.List<String> codigo;
	
	private java.util.List<String> descricao;
	
	private Boolean ativoIsNotNull;
	
	private Boolean ativoIsNull;
	
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
	
	public void setAtivoIsNotNull(Boolean ativoIsNotNull) {
		this.ativoIsNotNull = ativoIsNotNull;
	}
	
	public Boolean isAtivoIsNull() {
		return ativoIsNull != null && ativoIsNull;
	}
	
	public void setAtivoIsNull(Boolean ativoIsNull) {
		this.ativoIsNull = ativoIsNull;
	}
	

}
