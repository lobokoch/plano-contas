package br.com.kerubin.api.financeiro.planocontas.service;

import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.querydsl.jpa.JPAExpressions;
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
	public List<PlanoContaTreeNode> loadTree() {
		QPlanoContaEntity qPlanoContaEntity = QPlanoContaEntity.planoContaEntity;
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		
		// TODO: database can do this for us, but Hibernate does not help.
		//OrderSpecifier<Integer> order = Expressions.stringTemplate("(replace({0}, '.', ''))", qPlanoContaEntity.codigo).castToNum(Integer.class).asc();
		
		List<PlanoContaEntity> entities = queryFactory
			.selectFrom(qPlanoContaEntity)
			.where(qPlanoContaEntity.ativo.eq(true))
			.orderBy(qPlanoContaEntity.codigo.asc())
			//.orderBy(order)
			.fetch();
		
		entities = entities.stream()
			.sorted(Comparator.comparingInt(this::planoContaCodigoToInt))
			.collect(Collectors.toList());
		
		//entities = entities.stream().sorted(Comparator.comparing(PlanoContaEntity::getCodigo)).collect(Collectors.toList());
			
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
			PlanoContaTreeNode node = new PlanoContaTreeNode(parentEntity.getId().toString(), label, parentEntity.getCodigo());
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
			PlanoContaTreeNode childNode = new PlanoContaTreeNode(childEntity.getId().toString(), label, childEntity.getCodigo());
			parentNode.addNode(childNode);
			
			loadChildNodes(childNode, childEntity, entities);
		});
		
	}
	
	@Transactional(readOnly = true)
	@Override
	public PlanoContaTreeNode loadNode(UUID id) {
		QPlanoContaEntity qPlanoContaEntity = QPlanoContaEntity.planoContaEntity;
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		
		
		List<PlanoContaEntity> entities = queryFactory
			.selectFrom(qPlanoContaEntity)
			.where(qPlanoContaEntity.codigo.startsWithIgnoreCase(
					JPAExpressions.select(qPlanoContaEntity.codigo).from(qPlanoContaEntity).where(qPlanoContaEntity.id.eq(id))
			))
			.orderBy(qPlanoContaEntity.codigo.asc())
			//.orderBy(Expressions.stringTemplate("replace({0}, '.', '')", qPlanoContaEntity.codigo).castToNum(Integer.class).asc())
			.fetch();
		
		entities = entities.stream()
				.sorted(Comparator.comparingInt(this::planoContaCodigoToInt))
				.collect(Collectors.toList());
		
		PlanoContaTreeNode node = null;
		if (!CollectionUtils.isEmpty(entities)) {
			PlanoContaEntity parentEntity = entities.get(0);
			String label = parentEntity.getCodigo() + " - " + parentEntity.getDescricao();
			node = new PlanoContaTreeNode(parentEntity.getId().toString(), label, parentEntity.getCodigo());

			List<PlanoContaEntity> childs = entities.stream().skip(1).collect(Collectors.toList());
			loadChildNodes(node, parentEntity, childs);
		}
		
		return node;
	}
	
	private int planoContaCodigoToInt(Object planoContaEntityObj) {
		if (planoContaEntityObj != null && planoContaEntityObj instanceof PlanoContaEntity) {
			PlanoContaEntity planoContaEntity = (PlanoContaEntity) planoContaEntityObj;
			String codigo = planoContaEntity.getCodigo();
			if (isNotEmpty(codigo)) {
				codigo = codigo.replace(".", "");
				try {
					return Integer.parseInt(codigo);
				} catch(Exception e) {
					return 0;
				}
			}
		}
		
		return 0;
	}

}
