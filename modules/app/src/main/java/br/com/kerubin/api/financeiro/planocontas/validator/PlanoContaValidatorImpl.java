package br.com.kerubin.api.financeiro.planocontas.validator;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.planocontas.exception.PlanoContasException;
import br.com.kerubin.api.financeiro.planocontas.repository.PlanoContaRepository;
import static br.com.kerubin.api.servicecore.util.CoreUtils.*;

@Component
public class PlanoContaValidatorImpl implements PlanoContaValidator {
	
	@Inject
	private PlanoContaRepository planoContaRepository;

	@Override
	public void validate(PlanoContaEntity planoContaEntity) {
		if (isEmpty(planoContaEntity)) {
			throw new PlanoContasException("O plano de contas deve ser informado.");
		}
		
		if (isEmpty(planoContaEntity.getCodigo())) {
			throw new PlanoContasException("O código do plano de contas deve ser informado.");
		}
		
		if (isEmpty(planoContaEntity.getDescricao())) {
			throw new PlanoContasException("A descrição do plano de contas deve ser informada.");
		}
		
		if(isNotEmpty(planoContaEntity.getPlanoContaPai())) {
			PlanoContaEntity planoContaEntityPai = planoContaRepository.findById(planoContaEntity.getPlanoContaPai().getId()).orElse(null);
			if (!planoContaEntityPai.getTipoFinanceiro().equals(planoContaEntity.getTipoFinanceiro())) {
				throw new PlanoContasException("O \"Tipo financeiro\" do plano de contas deve ser o mesmo do plano de contas pai.");
			}
		}
		
	}
	
	
	
	
}
