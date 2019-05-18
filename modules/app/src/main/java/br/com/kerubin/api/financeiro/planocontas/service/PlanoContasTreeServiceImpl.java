package br.com.kerubin.api.financeiro.planocontas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.QPlanoContaEntity;
import br.com.kerubin.api.financeiro.planocontas.model.PlanoContaTreeNode;

@Service
public class PlanoContasTreeServiceImpl implements PlanoContasTreeService {
	
	@Inject
	private EntityManager em;
	
	@Transactional(readOnly = true)
	@Override
	public List<PlanoContaTreeNode> loadItems() {
		QPlanoContaEntity qPlanoContaEntity = QPlanoContaEntity.planoContaEntity;
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		
		List<PlanoContaEntity> entities = queryFactory
			.selectFrom(qPlanoContaEntity)
			.where(qPlanoContaEntity.ativo.eq(true))
			.orderBy(qPlanoContaEntity.codigo.asc())
			.fetch();
			
		List<PlanoContaEntity> parents = entities
				.stream()
				.filter(it -> it.getPlanoContaPai() == null)
				.collect(Collectors.toList());
		
		List<PlanoContaEntity> childEntities = entities
				.stream()
				.filter(it -> it.getPlanoContaPai() != null)
				.collect(Collectors.toList());
		
		
		List<PlanoContaTreeNode> nodes = new ArrayList<>();
		
		parents.forEach(parentEntity -> {
			String label = parentEntity.getCodigo() + " - " + parentEntity.getDescricao();
			PlanoContaTreeNode node = new PlanoContaTreeNode(parentEntity.getId().toString(), label);
			nodes.add(node);
			
			loadChildNodes(node, parentEntity, childEntities);
		});
		
		return nodes;
	}

	private void loadChildNodes(PlanoContaTreeNode parentNode, PlanoContaEntity parentEntity, List<PlanoContaEntity> entities) {
 		List<PlanoContaEntity> childs = entities.stream()
				.filter(childEntity -> {
					PlanoContaEntity planoContaPai = childEntity.getPlanoContaPai();
					boolean result = planoContaPai.getId().equals(parentEntity.getId());
					return result;
				})
				.collect(Collectors.toList());
		
		childs.forEach(childEntity -> {
			String label = childEntity.getCodigo() + " - " + childEntity.getDescricao();
			PlanoContaTreeNode childNode = new PlanoContaTreeNode(childEntity.getId().toString(), label);
			parentNode.addNode(childNode);
			
			loadChildNodes(childNode, childEntity, entities);
		});
		
	}

}
