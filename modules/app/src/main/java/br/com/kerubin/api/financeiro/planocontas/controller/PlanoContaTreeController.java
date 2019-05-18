package br.com.kerubin.api.financeiro.planocontas.controller;

import java.util.Collection;

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
		
		Collection<PlanoContaTreeNode> nodes = planoContasTreeService.loadItems();
		
		return nodes;
	}

}
