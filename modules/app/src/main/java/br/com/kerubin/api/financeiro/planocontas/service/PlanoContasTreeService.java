package br.com.kerubin.api.financeiro.planocontas.service;

import java.util.List;

import br.com.kerubin.api.financeiro.planocontas.model.PlanoContaTreeNode;

public interface PlanoContasTreeService {

	List<PlanoContaTreeNode> loadItems();

}
