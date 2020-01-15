/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:56:10.144
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import br.com.kerubin.api.database.entity.AuditingEntity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.kerubin.api.financeiro.planocontas.TipoPlanoContaFinanceiro;
import br.com.kerubin.api.financeiro.planocontas.TipoReceitaDespesa;
import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaEntity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "plano_conta")
public class PlanoContaEntity extends AuditingEntity {

	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@NotBlank(message="\"Código\" é obrigatório.")
	@Size(max = 255, message = "\"Código\" pode ter no máximo 255 caracteres.")
	@Column(name="codigo")
	private String codigo;
	
	@NotBlank(message="\"Descrição\" é obrigatório.")
	@Size(max = 255, message = "\"Descrição\" pode ter no máximo 255 caracteres.")
	@Column(name="descricao")
	private String descricao;
	
	@NotNull(message="\"tipoFinanceiro\" é obrigatório.")
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_financeiro")
	private TipoPlanoContaFinanceiro tipoFinanceiro;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_receita_despesa")
	private TipoReceitaDespesa tipoReceitaDespesa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plano_conta_pai")
	private PlanoContaEntity planoContaPai;
	
	@NotNull(message="\"Ativo\" é obrigatório.")
	@Column(name="ativo")
	private Boolean ativo = true;
	
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
	
	public PlanoContaEntity getPlanoContaPai() {
		return planoContaPai;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo != null ? codigo.trim() : codigo; // Chamadas REST fazem trim.
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao != null ? descricao.trim() : descricao; // Chamadas REST fazem trim.
	}
	
	public void setTipoFinanceiro(TipoPlanoContaFinanceiro tipoFinanceiro) {
		this.tipoFinanceiro = tipoFinanceiro;
	}
	
	public void setTipoReceitaDespesa(TipoReceitaDespesa tipoReceitaDespesa) {
		this.tipoReceitaDespesa = tipoReceitaDespesa;
	}
	
	public void setPlanoContaPai(PlanoContaEntity planoContaPai) {
		this.planoContaPai = planoContaPai;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public void assign(PlanoContaEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setCodigo(source.getCodigo());
			this.setDescricao(source.getDescricao());
			this.setTipoFinanceiro(source.getTipoFinanceiro());
			this.setTipoReceitaDespesa(source.getTipoReceitaDespesa());
			this.setPlanoContaPai(source.getPlanoContaPai());
			this.setAtivo(source.getAtivo());
			this.setCreatedBy(source.getCreatedBy());
			this.setCreatedDate(source.getCreatedDate());
			this.setLastModifiedBy(source.getLastModifiedBy());
			this.setLastModifiedDate(source.getLastModifiedDate());
		}
	}
	
	public PlanoContaEntity clone() {
		return clone(new java.util.HashMap<>());
	}
	
	public PlanoContaEntity clone(java.util.Map<Object, Object> visited) {
		if (visited.containsKey(this)) {
			return (PlanoContaEntity) visited.get(this);
		}
				
		PlanoContaEntity theClone = new PlanoContaEntity();
		visited.put(this, theClone);
		
		theClone.setId(this.getId());
		theClone.setCodigo(this.getCodigo());
		theClone.setDescricao(this.getDescricao());
		theClone.setTipoFinanceiro(this.getTipoFinanceiro());
		theClone.setTipoReceitaDespesa(this.getTipoReceitaDespesa());
		theClone.setPlanoContaPai(this.getPlanoContaPai() != null ? this.getPlanoContaPai().clone(visited) : null);
		theClone.setAtivo(this.getAtivo());
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
