
package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.financeiro.planocontas.ObjectMapper;

@Component
public class PlanoContaDTOConverter {
	@Inject

	private ObjectMapper mapper;

	public PlanoConta convertEntityToDto(PlanoContaEntity entity) {
		PlanoConta dto = null;
		if (entity != null) {
			dto = mapper.map(entity, PlanoConta.class);
		}
		return dto;
	}


	public PlanoContaEntity convertDtoToEntity(PlanoConta dto) {
		PlanoContaEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, PlanoContaEntity.class);
		}
		return entity;
	}


}