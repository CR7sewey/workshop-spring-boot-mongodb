package com.miguelcarvalho.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {
	
	public static String decodeParam(String text) { // esattico para nao precisar de instanciar um objto URL

		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			return "";
		}
	}
	
	public static Date convertDate(String textData, Date defaultValue) { // esattico para nao precisar de instanciar um objto URL
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(textData);
		} catch (ParseException e) {
			
			return defaultValue;
		}
	}
}
