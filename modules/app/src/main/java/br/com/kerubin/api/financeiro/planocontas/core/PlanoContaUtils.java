package br.com.kerubin.api.financeiro.planocontas.core;

import static br.com.kerubin.api.financeiro.planocontas.core.Utils.isEmpty;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlanoContaUtils {
	
	public static List<String> getGruposAsString(String codigo) {
		if (isEmpty(codigo)) {
			return Collections.emptyList();
		}
		
		List<String> grupos = Arrays.asList(codigo.split("\\."));
		return grupos;
	}
	
	

}
