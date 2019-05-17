package br.com.kerubin.api.financeiro.planocontas.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
	
	private static final List<String> EXCLUDED_ARTIGOS = Arrays.asList("de", "do", "dos", "da", "das");
	
	public static boolean isNotEmpty(Object value) {
		return !isEmpty(value);
	}
	
	public static boolean isEmpty(Object value) {
		if (value == null) {
			return true;
		}
		
		if (value instanceof String) {
			return ((String) value).trim().isEmpty();
		}
		
		if (value instanceof Collection) {
			return ((Collection<?>) value).isEmpty();
		}
		
		return false;
	}
	
	public static String toFirstUpper(String str) {
		if (isEmpty(str)) {
			return null;
		}
		
		String result = str.substring(0, 1).toUpperCase() + str.substring(1);
		return result;
	}
	
	public static String joinStringsAsCamelCase(List<String> strings) {
		String result = strings
				.stream()
				.map(it -> {
					if (it.length() <= 2 || EXCLUDED_ARTIGOS.contains(it.toLowerCase())) {
						return it;
					}
					return toFirstUpper(it);
				}).collect(Collectors.joining(" "));
		return result;
	}

}
