package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface PlanoContaRepository extends JpaRepository<PlanoContaEntity, java.util.UUID>, QuerydslPredicateExecutor<PlanoContaEntity> {
	
	@Query("select distinct ac.id as id, ac.code as code from PlanoContaEntity ac where ( upper(ac.code) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<PlanoContaAutoComplete> autoComplete(@Param("query") String query);
	
}
