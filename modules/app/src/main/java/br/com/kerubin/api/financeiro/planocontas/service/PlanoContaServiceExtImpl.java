package br.com.kerubin.api.financeiro.planocontas.service;

import static br.com.kerubin.api.financeiro.planocontas.core.PlanoContaUtils.getGruposAsString;
import static br.com.kerubin.api.financeiro.planocontas.core.Utils.joinStringsAsCamelCase;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaServiceImpl;
import br.com.kerubin.api.financeiro.planocontas.exception.PlanoContasException;
import br.com.kerubin.api.financeiro.planocontas.validator.PlanoContaValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary
@Service
public class PlanoContaServiceExtImpl extends PlanoContaServiceImpl {
	
	/*@Inject
	private PlanoContaRepository planoContaRepository;*/
	
	@Inject
	private PlanoContaValidator planoContaValidator;
	
	@Override
	public PlanoContaEntity create(PlanoContaEntity planoContaEntity) {
		
		planoContaValidator.validate(planoContaEntity);
		
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
		planoContaValidator.validate(planoContaEntity);
		ajustarCaixaCodigoConta(planoContaEntity);
		
		return super.update(id, planoContaEntity);
	}
	
	@Transactional
	@Override
	public void delete(UUID id) {
		try {
			super.delete(id);
		} catch(DataIntegrityViolationException e) {
			log.error("Error deleting Plano de contas id: " + id + ", error:" + e.getMessage(), e);
			throw new PlanoContasException("O plano de contas não pode ser excluído devido a estar sendo usado ou possuir filhos.");
		} catch (Exception e) {
			log.error("Error deleting Plano de contas id: " + id + ", error:" + e.getMessage(), e);
			throw new PlanoContasException("Erro ao excluir o plano de contas.");
		}
		
	}

}
