import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextUppercaseLowercase {

	public static String textUppercaseLowercase(String text) {
		String result = "Not matched!";
		int i = 0;
		while (i < text.length()) {
			if (Character.isUpperCase(text.charAt(i))) {
				if (i + 1 < text.length()
						&& Character.isLowerCase(text.charAt(i + 1))) {
					result = "Found a match!";
					break;
				}
			}
			i++;
		}
		return result;
	}
}
