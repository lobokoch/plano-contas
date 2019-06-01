package br.com.kerubin.api.financeiro.planocontas.validator;

import org.springframework.stereotype.Component;

import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaEntity;

@Component
public class PlanoContaValidatorImpl implements PlanoContaValidator {
	
	/*@Inject
	private PlanoContaRepository planoContaRepository;*/

	@Override
	public void validate(PlanoContaEntity planoContaEntity) {
		/* TODO: tirar a validação por enquanto para flexibilidade.
		UUID id = planoContaEntity.getId() != null ? planoContaEntity.getId() : UUID.randomUUID(); // When is new, id will be null and get an exception due this.
		Optional<PlanoContaEntity> other = planoContaRepository.findByCodigoAndIdNot(planoContaEntity.getCodigo(), id);
		if (other.isPresent()) {
			PlanoContaEntity othetEntity = other.get();
			String itemA = planoContaEntity.getCodigo() + " - " + planoContaEntity.getDescricao();
			String itemB = othetEntity.getCodigo() + " - " + othetEntity.getDescricao();
			throw new IllegalStateException("Não é possível gravar o item \"" + itemA + "\", devido a já existir o item \"" + itemB + "\" com o mesmo código.");
		}
		*/
	}
	
	
	
	
}
