/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:56:10.144
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.planocontas.entity.planoconta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PlanoContaBaseRepository extends JpaRepository<PlanoContaEntity, java.util.UUID>, QuerydslPredicateExecutor<PlanoContaEntity> {
	
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.id as id, ac.codigo as codigo, ac.descricao as descricao from PlanoContaEntity ac where ( upper(ac.codigo) like upper(concat('%', :query, '%')) ) or ( upper(ac.descricao) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<PlanoContaAutoComplete> autoComplete(@Param("query") String query);
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.codigo as codigo from PlanoContaEntity ac where ( upper(ac.codigo) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<PlanoContaCodigoAutoComplete> planoContaCodigoAutoComplete(@Param("query") String query);
	// WARNING: supports only where clause with like for STRING fields. For relationships entities will get the first string autocomplete key field name.
	@Query("select distinct ac.descricao as descricao from PlanoContaEntity ac where ( upper(ac.descricao) like upper(concat('%', :query, '%')) ) order by 1 asc")
	Collection<PlanoContaDescricaoAutoComplete> planoContaDescricaoAutoComplete(@Param("query") String query);
}
