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

 
@Service
public class PlanoContaServiceImpl implements PlanoContaService {
	
	@Autowired
	private PlanoContaRepository planoContaRepository;
	
	@Autowired
	private PlanoContaListFilterPredicate planoContaListFilterPredicate;
	
	
	
	@Transactional
	public PlanoContaEntity create(PlanoContaEntity planoContaEntity) {
		return planoContaRepository.save(planoContaEntity);
	}
	
	@Transactional(readOnly = true)
	public PlanoContaEntity read(java.util.UUID id) {
		return getPlanoContaEntity(id);
	}
	
	@Transactional
	public PlanoContaEntity update(java.util.UUID id, PlanoContaEntity planoContaEntity) {
		PlanoContaEntity entity = getPlanoContaEntity(id);
		BeanUtils.copyProperties(planoContaEntity, entity, "id");
		entity = planoContaRepository.save(entity);
		
		return entity;
	}
	
	@Transactional
	public void delete(java.util.UUID id) {
		planoContaRepository.deleteById(id);
		
	}
	
	
	@Transactional(readOnly = true)
	public Page<PlanoContaEntity> list(PlanoContaListFilter planoContaListFilter, Pageable pageable) {
		Predicate predicate = planoContaListFilterPredicate.mountAndGetPredicate(planoContaListFilter);
		
		Page<PlanoContaEntity> resultPage = planoContaRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	private PlanoContaEntity getPlanoContaEntity(java.util.UUID id) {
		Optional<PlanoContaEntity> planoContaEntity = planoContaRepository.findById(id);
		if (!planoContaEntity.isPresent()) {
			throw new IllegalArgumentException("PlanoConta not found:" + id.toString());
		}
		return planoContaEntity.get();
	}
	
	@Transactional(readOnly = true)
	public Collection<PlanoContaAutoComplete> autoComplete(String query) {
		Collection<PlanoContaAutoComplete> result = planoContaRepository.autoComplete(query);
		return result;
	}
	
	
}
