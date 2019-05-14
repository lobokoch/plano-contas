package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import br.com.kerubin.api.database.entity.AuditingEntity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaEntity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.kerubin.api.financeiro.planocontas.PlanoContaKind;

@Entity
@Table(name = "plano_conta")
public class PlanoContaEntity extends AuditingEntity {

	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@Column(name="code")
	private String code;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_plano_conta")
	private PlanoContaEntity parentPlanoConta;
	
	@Column(name="active")
	private Boolean active;
	
	@Enumerated(EnumType.STRING)
	@Column(name="kind")
	private PlanoContaKind kind;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	public PlanoContaEntity getParentPlanoConta() {
		return parentPlanoConta;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public PlanoContaKind getKind() {
		return kind;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setCode(String code) {
		this.code = code != null ? code.trim() : code; // Chamadas REST fazem trim.
	}
	
	public void setDescription(String description) {
		this.description = description != null ? description.trim() : description; // Chamadas REST fazem trim.
	}
	
	public void setParentPlanoConta(PlanoContaEntity parentPlanoConta) {
		this.parentPlanoConta = parentPlanoConta;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public void setKind(PlanoContaKind kind) {
		this.kind = kind;
	}
	
	public void assign(PlanoContaEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setCode(source.getCode());
			this.setDescription(source.getDescription());
			this.setParentPlanoConta(source.getParentPlanoConta());
			this.setActive(source.getActive());
			this.setKind(source.getKind());
			this.setCreatedBy(source.getCreatedBy());
			this.setCreatedDate(source.getCreatedDate());
			this.setLastModifiedBy(source.getLastModifiedBy());
			this.setLastModifiedDate(source.getLastModifiedDate());
		}
	}
	
	public PlanoContaEntity clone() {
		PlanoContaEntity theClone = new PlanoContaEntity();
		theClone.setId(this.getId());
		theClone.setCode(this.getCode());
		theClone.setDescription(this.getDescription());
		theClone.setParentPlanoConta(this.getParentPlanoConta());
		theClone.setActive(this.getActive());
		theClone.setKind(this.getKind());
		theClone.setCreatedBy(this.getCreatedBy());
		theClone.setCreatedDate(this.getCreatedDate());
		theClone.setLastModifiedBy(this.getLastModifiedBy());
		theClone.setLastModifiedDate(this.getLastModifiedDate());
		
		return theClone;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanoContaEntity other = (PlanoContaEntity) obj;
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
