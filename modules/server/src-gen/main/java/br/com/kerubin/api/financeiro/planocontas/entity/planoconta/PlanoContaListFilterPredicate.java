package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;

import com.querydsl.core.types.Predicate;

public interface PlanoContaListFilterPredicate {
	
	Predicate mountAndGetPredicate(PlanoContaListFilter planoContaListFilter);

}

