package com.code.urlshorten.urlshorten.util;

public class Base62Encoder {

	private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	
	public static String endcode(long value) {
		
		StringBuilder sbb = new StringBuilder();
		
		while(value > 0) {
			sbb.append(BASE62.charAt((int)(value % 62)));
			value /= 62;
		}
		
		return sbb.reverse().toString();
	}
	
	
}
