/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:56:10.144
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;

// import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.messaging.core.DomainEvent;
import br.com.kerubin.api.messaging.core.DomainEventEnvelope;
import br.com.kerubin.api.messaging.core.DomainEventEnvelopeBuilder;
import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.financeiro.planocontas.FinanceiroPlanoContasConstants;



import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
 
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
	@Override
	public PlanoContaEntity create(PlanoContaEntity planoContaEntity) {
		PlanoContaEntity entity = planoContaBaseRepository.save(planoContaEntity);
		publishEvent(entity, PlanoContaEvent.PLANO_CONTA_CREATED);
		return entity;
	}
	
	@Transactional(readOnly = true)
	@Override
	public PlanoContaEntity read(java.util.UUID id) {
		return getPlanoContaEntity(id);
	}
	
	@Transactional
	@Override
	public PlanoContaEntity update(java.util.UUID id, PlanoContaEntity planoContaEntity) {
		// PlanoContaEntity entity = getPlanoContaEntity(id);
		// BeanUtils.copyProperties(planoContaEntity, entity, "id");
		// entity = planoContaBaseRepository.save(entity);
		
		PlanoContaEntity entity = planoContaBaseRepository.save(planoContaEntity);
		
		publishEvent(entity, PlanoContaEvent.PLANO_CONTA_UPDATED);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		
		// First load the delete candidate entity.
		PlanoContaEntity entity = getPlanoContaEntity(id);
		
		// Delete it.
		planoContaBaseRepository.deleteById(id);
		
		// Force flush to the database, for relationship validation and must throw exception because of this here.
		planoContaBaseRepository.flush();
		
		// Replicate the delete event.
		publishEvent(entity, PlanoContaEvent.PLANO_CONTA_DELETED);
	}
	
	protected void publishEvent(PlanoContaEntity entity, String eventName) {
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
	@Override
	public Page<PlanoContaEntity> list(PlanoContaListFilter planoContaListFilter, Pageable pageable) {
		Predicate predicate = planoContaListFilterPredicate.mountAndGetPredicate(planoContaListFilter);
		
		Page<PlanoContaEntity> resultPage = planoContaBaseRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected PlanoContaEntity getPlanoContaEntity(java.util.UUID id) {
		Optional<PlanoContaEntity> planoContaEntity = planoContaBaseRepository.findById(id);
		if (!planoContaEntity.isPresent()) {
			throw new IllegalArgumentException("PlanoConta not found:" + id.toString());
		}
		return planoContaEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<PlanoContaAutoComplete> autoComplete(String query) {
		Collection<PlanoContaAutoComplete> result = planoContaBaseRepository.autoComplete(query);
		return result;
	}
	
	// Begin relationships autoComplete 
	@Transactional(readOnly = true)
	@Override
	public Collection<PlanoContaAutoComplete> planoContaPlanoContaPaiAutoComplete(String query) {
		Collection<PlanoContaAutoComplete> result = planoContaBaseRepository.autoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
	
	@Transactional(readOnly = true)
	@Override
	public Collection<PlanoContaCodigoAutoComplete> planoContaCodigoAutoComplete(String query) {
		Collection<PlanoContaCodigoAutoComplete> result = planoContaBaseRepository.planoContaCodigoAutoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<PlanoContaDescricaoAutoComplete> planoContaDescricaoAutoComplete(String query) {
		Collection<PlanoContaDescricaoAutoComplete> result = planoContaBaseRepository.planoContaDescricaoAutoComplete(query);
		return result;
	}
	
	
}
