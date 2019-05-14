package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface PlanoContaService {
	
	public PlanoContaEntity create(PlanoContaEntity planoContaEntity);
	
	public PlanoContaEntity read(java.util.UUID id);
	
	public PlanoContaEntity update(java.util.UUID id, PlanoContaEntity planoContaEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<PlanoContaEntity> list(PlanoContaListFilter planoContaListFilter, Pageable pageable);
	
	public Collection<PlanoContaAutoComplete> autoComplete(String query);
}
