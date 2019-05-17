package br.com.kerubin.api.financeiro.planocontas.service;

import static br.com.kerubin.api.financeiro.planocontas.core.PlanoContaUtils.*;
import static br.com.kerubin.api.financeiro.planocontas.core.Utils.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaServiceImpl;

@Primary
@Service
public class PlanoContaServiceExtImpl extends PlanoContaServiceImpl {
	
	@Override
	public PlanoContaEntity create(PlanoContaEntity planoContaEntity) {
		ajustarCaixaCodigoConta(planoContaEntity);
		
		return super.create(planoContaEntity);
	}

	private void ajustarCaixaCodigoConta(PlanoContaEntity planoContaEntity) {
		List<String> strings = Arrays.asList(planoContaEntity.getDescricao().split(" "));
		String descricao = joinStringsAsCamelCase(strings);
		planoContaEntity.setDescricao(descricao);
		
		List<String> grupos = getGruposAsString(planoContaEntity.getCodigo());
		if (grupos.size() <= 2) {
			planoContaEntity.setDescricao(planoContaEntity.getDescricao().toUpperCase());
		}		
	}
	
	@Override
	public PlanoContaEntity update(UUID id, PlanoContaEntity planoContaEntity) {
		ajustarCaixaCodigoConta(planoContaEntity);
		
		return super.update(id, planoContaEntity);
	}

}
