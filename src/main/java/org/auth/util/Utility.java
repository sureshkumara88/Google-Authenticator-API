package org.auth.util;

/**
 * This class is used to provide common utility feature through-out the application 
 * @author suresh kumar
 *
 */
public class Utility {

	/**
	 * This method is used to padding '0' in front till the given length matches
	 * @param number
	 * @param length
	 * @return
	 */
	public static String padNumber(String number, int length) {
		StringBuilder result = new StringBuilder("");
		int requiredPadding = length - number.length();
		
		for (int padding = 0; padding < requiredPadding; padding++) {
			result.append("0");
		}
		
		result.append(number);
		
		return result.toString();
	}
}
