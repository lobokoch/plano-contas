/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:56:10.144
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PlanoContaService {
	
	public PlanoContaEntity create(PlanoContaEntity planoContaEntity);
	
	public PlanoContaEntity read(java.util.UUID id);
	
	public PlanoContaEntity update(java.util.UUID id, PlanoContaEntity planoContaEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<PlanoContaEntity> list(PlanoContaListFilter planoContaListFilter, Pageable pageable);
	
	public Collection<PlanoContaAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<PlanoContaAutoComplete> planoContaPlanoContaPaiAutoComplete(String query);
	// End relationships autoComplete
	 
	
	public Collection<PlanoContaCodigoAutoComplete> planoContaCodigoAutoComplete(String query);
	
	public Collection<PlanoContaDescricaoAutoComplete> planoContaDescricaoAutoComplete(String query);
}
