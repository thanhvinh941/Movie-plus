package com.movieplus.domain.common;

public class Util {
	
	private Util() {
		throw new IllegalStateException("Utility class");
	}

	public static String convertSnake(String str) {
		if (str.contains("_") || str.isEmpty()) {
			return str;
		} else {

			if (Character.isUpperCase(str.charAt(0))) {
				char[] c = str.toCharArray();
				c[0] += 32;
				str = new String(c);
			}
			str = str.replaceAll("([^A-Z])([A-Z0-9])", "$1_$2")
					.replaceAll("([A-Z]+)([A-Z0-9][^A-Z]+)", "$1_$2")
					.replaceAll("([0-9]+)([a-zA-Z]+)", "$1_$2")
					.toLowerCase();

		}
		return str;
	}
}
