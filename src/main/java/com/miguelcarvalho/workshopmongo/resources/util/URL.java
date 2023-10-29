package com.miguelcarvalho.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {
	
	public static String decodeParam(String text) { // esattico para nao precisar de instanciar um objto URL

		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			return "";
		}
	}
}
