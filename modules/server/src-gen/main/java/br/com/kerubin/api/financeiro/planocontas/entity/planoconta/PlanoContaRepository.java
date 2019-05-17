package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

public interface PlanoContaRepository extends JpaRepository<PlanoContaEntity, java.util.UUID>, QuerydslPredicateExecutor<PlanoContaEntity> {
	
	@Query("select distinct ac.id as id, ac.codigo as codigo, ac.descricao as descricao from PlanoContaEntity ac where ( upper(ac.codigo) like upper(concat('%', :query, '%')) ) or ( upper(ac.descricao) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<PlanoContaAutoComplete> autoComplete(@Param("query") String query);
	
	@Query("select distinct ac.codigo as codigo from PlanoContaEntity ac where ( upper(ac.codigo) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<PlanoContaCodigoAutoComplete> planoContaCodigoAutoComplete(@Param("query") String query);
	
	@Query("select distinct ac.descricao as descricao from PlanoContaEntity ac where ( upper(ac.descricao) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<PlanoContaDescricaoAutoComplete> planoContaDescricaoAutoComplete(@Param("query") String query);
	
}
