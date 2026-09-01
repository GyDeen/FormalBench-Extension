import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextUppercaseLowercase {

	public static String textUppercaseLowercase(String text) {
		String result = "Not matched!";
		int index = 0;
		while (index < text.length()) {
			if (Character.isUpperCase(text.charAt(index))) {
				if (index + 1 < text.length()
						&& Character.isLowerCase(text.charAt(index + 1))) {
					result = "Found a match!";
					break;
				}
			}
			index++;
		}
		return result;
	}
}
