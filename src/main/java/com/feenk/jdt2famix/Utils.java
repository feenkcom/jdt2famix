package com.feenk.jdt2famix;

/**
 * A set of utilities to circumvent missing APIs in base Java classes
 * @author girba
 *
 */
public class Utils {
	public static String abbreviate(String string, int charCount) {
		return string.substring(0, Math.min(string.length(), charCount));
	}
}
