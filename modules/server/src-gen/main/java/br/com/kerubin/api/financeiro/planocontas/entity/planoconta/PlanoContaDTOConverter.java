
package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;

import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Component
public class PlanoContaDTOConverter {
	private final ModelMapper mapper;

	public PlanoContaDTOConverter() {
		mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		
		
	}


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