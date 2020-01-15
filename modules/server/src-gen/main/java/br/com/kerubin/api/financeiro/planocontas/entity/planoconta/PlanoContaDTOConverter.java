/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:56:10.144
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.servicecore.mapper.ObjectMapper;

@Component
public class PlanoContaDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public PlanoConta convertEntityToDto(PlanoContaEntity entity) {
		PlanoConta dto = null;
		if (entity != null) {
			dto = mapper.map(entity, PlanoConta.class, true); // Do not permit passwords fields go outside.
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