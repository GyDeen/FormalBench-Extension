import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextUppercaseLowercase {

	public static String textUppercaseLowercase(String text) {
		String result = "Not matched!";
		for (int i = 0; i < text.length(); i++) {
			if (Character.isUpperCase(text.charAt(i))) {
				if (i + 1 < text.length()) {
					if (Character.isLowerCase(text.charAt(i + 1))) {
						result = "Found a match!";
						break;
					}
				}
			}
		}
		return result;
	}
}
