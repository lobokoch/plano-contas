package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;


import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;

@Component
public class PlanoContaListFilterPredicateImpl implements PlanoContaListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(PlanoContaListFilter planoContaListFilter) {
		if (planoContaListFilter == null) {
			return null;
		}
		
		BooleanBuilder where = new BooleanBuilder();
		
		
		return where;
	}

}

