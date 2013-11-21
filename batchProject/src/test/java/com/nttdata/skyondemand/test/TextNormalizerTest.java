package com.nttdata.skyondemand.test;

import java.text.Normalizer;

public class TextNormalizerTest {

	public static void main(String[] args) {
		final String input = "Tĥïŝ' ĩš â ':  fůňķŷ Šťŕĭńġ";
		System.out.println(
		    Normalizer
		        .normalize(input.toLowerCase(), Normalizer.Form.NFD)
		        .replaceAll("[^\\p{ASCII}]", "")
		        .replaceAll("[^\\w]+", "_")
		);
		
		final String input2 = "/ondemand/i_piu_visti/kasjda.html";
		final String regex = ".*(scelti_per_te|i_piu_visti|ultimi_arrivi|serie_complete).*";
//		final String regex = ".*[i_piu_visti]|[scelti_per_te].*";
		
		System.out.println(input2.matches(regex));
		
	}

}
