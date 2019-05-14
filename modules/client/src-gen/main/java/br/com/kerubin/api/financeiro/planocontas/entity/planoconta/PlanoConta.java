package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;

import javax.validation.constraints.NotBlank;
import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaLookupResult;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.planocontas.PlanoContaKind;

public class PlanoConta {

	private java.util.UUID id;
	
	@NotBlank(message="'Código' é obrigatório.")
	private String code;
	
	@NotBlank(message="'Descrição' é obrigatório.")
	private String description;
	
	private PlanoContaLookupResult parentPlanoConta;
	
	@NotNull(message="'Ativo' é obrigatório.")
	private Boolean active;
	
	@NotNull(message="'Tipo da conta' é obrigatório.")
	private PlanoContaKind kind;
	
	private String createdBy;
	
	private java.time.LocalDateTime createdDate;
	
	private String lastModifiedBy;
	
	private java.time.LocalDateTime lastModifiedDate;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public PlanoContaLookupResult getParentPlanoConta() {
		return parentPlanoConta;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public PlanoContaKind getKind() {
		return kind;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public java.time.LocalDateTime getCreatedDate() {
		return createdDate;
	}
	
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	
	public java.time.LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setParentPlanoConta(PlanoContaLookupResult parentPlanoConta) {
		this.parentPlanoConta = parentPlanoConta;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public void setKind(PlanoContaKind kind) {
		this.kind = kind;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public void setCreatedDate(java.time.LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	public void setLastModifiedDate(java.time.LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanoConta other = (PlanoConta) obj;
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
