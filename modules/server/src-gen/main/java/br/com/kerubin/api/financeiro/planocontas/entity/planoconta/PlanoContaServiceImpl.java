/**********************************************************************************************
Code generated with MKL Plug-in version: 3.5.1
Code generated at time stamp: 2019-06-01T15:26:43.922
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;		

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import java.util.Optional;
import java.util.Collection;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.messaging.core.DomainEvent;
import br.com.kerubin.api.messaging.core.DomainEventEnvelope;
import br.com.kerubin.api.messaging.core.DomainEventEnvelopeBuilder;
import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.financeiro.planocontas.FinanceiroPlanoContasConstants;

 
@Service
public class PlanoContaServiceImpl implements PlanoContaService {
	private static final String ENTITY_KEY = "entity.PlanoConta";
	
	@Autowired
	private PlanoContaBaseRepository planoContaBaseRepository;
	
	@Autowired
	private PlanoContaListFilterPredicate planoContaListFilterPredicate;
	
	@Autowired
	DomainEntityEventsPublisher publisher;
	
	
	@Transactional
	public PlanoContaEntity create(PlanoContaEntity planoContaEntity) {
		PlanoContaEntity entity = planoContaBaseRepository.save(planoContaEntity);
		publishEvent(entity, PlanoContaEvent.PLANO_CONTA_CREATED);
		return entity;
	}
	
	@Transactional(readOnly = true)
	public PlanoContaEntity read(java.util.UUID id) {
		return getPlanoContaEntity(id);
	}
	
	@Transactional
	public PlanoContaEntity update(java.util.UUID id, PlanoContaEntity planoContaEntity) {
		PlanoContaEntity entity = getPlanoContaEntity(id);
		BeanUtils.copyProperties(planoContaEntity, entity, "id");
		entity = planoContaBaseRepository.save(entity);
		
		publishEvent(entity, PlanoContaEvent.PLANO_CONTA_UPDATED);
		
		return entity;
	}
	
	@Transactional
	public void delete(java.util.UUID id) {
		planoContaBaseRepository.deleteById(id);
		
		PlanoContaEntity entity = new PlanoContaEntity();
		entity.setId(id);
		publishEvent(entity, PlanoContaEvent.PLANO_CONTA_DELETED);
	}
	
	private void publishEvent(PlanoContaEntity entity, String eventName) {
		DomainEvent event = new PlanoContaEvent(entity.getId(), 
			entity.getCodigo(), 
			entity.getDescricao(), 
			entity.getTipoFinanceiro(), 
			entity.getTipoReceitaDespesa(), 
			entity.getPlanoContaPai() != null ? entity.getPlanoContaPai().getId() : null, 
			entity.getAtivo());
		
		DomainEventEnvelope<DomainEvent> envelope = DomainEventEnvelopeBuilder
				.getBuilder(eventName, event)
				.domain(FinanceiroPlanoContasConstants.DOMAIN)
				.service(FinanceiroPlanoContasConstants.SERVICE)
				.key(ENTITY_KEY)
				.tenant(ServiceContext.getTenant())
				.user(ServiceContext.getUser())
				.build();
		
		publisher.publish(envelope);
	}
	
	@Transactional(readOnly = true)
	public Page<PlanoContaEntity> list(PlanoContaListFilter planoContaListFilter, Pageable pageable) {
		Predicate predicate = planoContaListFilterPredicate.mountAndGetPredicate(planoContaListFilter);
		
		Page<PlanoContaEntity> resultPage = planoContaBaseRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	private PlanoContaEntity getPlanoContaEntity(java.util.UUID id) {
		Optional<PlanoContaEntity> planoContaEntity = planoContaBaseRepository.findById(id);
		if (!planoContaEntity.isPresent()) {
			throw new IllegalArgumentException("PlanoConta not found:" + id.toString());
		}
		return planoContaEntity.get();
	}
	
	@Transactional(readOnly = true)
	public Collection<PlanoContaAutoComplete> autoComplete(String query) {
		Collection<PlanoContaAutoComplete> result = planoContaBaseRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	public Collection<PlanoContaCodigoAutoComplete> planoContaCodigoAutoComplete(String query) {
		Collection<PlanoContaCodigoAutoComplete> result = planoContaBaseRepository.planoContaCodigoAutoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	public Collection<PlanoContaDescricaoAutoComplete> planoContaDescricaoAutoComplete(String query) {
		Collection<PlanoContaDescricaoAutoComplete> result = planoContaBaseRepository.planoContaDescricaoAutoComplete(query);
		return result;
	}
	
	
}
