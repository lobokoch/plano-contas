package br.com.kerubin.api.financeiro.planocontas.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaBaseRepository;
import br.com.kerubin.api.financeiro.planocontas.entity.planoconta.PlanoContaEntity;

@Repository
public interface PlanoContaRepository extends PlanoContaBaseRepository {
	
	Optional<PlanoContaEntity> findByCodigoAndIdNot(String codigo, UUID id);

}
