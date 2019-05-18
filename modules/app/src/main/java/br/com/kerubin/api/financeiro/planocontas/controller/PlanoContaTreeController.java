package br.com.kerubin.api.financeiro.planocontas.controller;

import java.util.Collection;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.kerubin.api.financeiro.planocontas.model.PlanoContaTreeNode;
import br.com.kerubin.api.financeiro.planocontas.service.PlanoContasTreeService;

@RestController
@RequestMapping("entities/planoConta")
public class PlanoContaTreeController {
	
	@Inject
	private PlanoContasTreeService planoContasTreeService;
	
	@GetMapping("/getPlanoContasTree")
	public Collection<PlanoContaTreeNode> getPlanoContasTree(@RequestParam("query") String query) {
		
		Collection<PlanoContaTreeNode> tree = planoContasTreeService.loadTree();
		
		return tree;
	}
	
	@GetMapping("/getPlanoContasNode")
	public PlanoContaTreeNode getPlanoContasNode(@RequestParam("id") String idStr) {
		
		UUID id = UUID.fromString(idStr);
		PlanoContaTreeNode node = planoContasTreeService.loadNode(id);
		
		return node;
	}

}
