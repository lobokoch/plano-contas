package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;


public class PlanoContaLookupResult {

	private java.util.UUID id;
	
	private String codigo;
	
	private String descricao;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanoContaLookupResult other = (PlanoContaLookupResult) obj;
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
