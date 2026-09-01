import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextUppercaseLowercase {

	public static String textUppercaseLowercase(String text) {
		String result = "Not matched!";
		for (int i = 0; i < text.length(); i++) {
			if (Character.isUpperCase(text.charAt(i))) {
				boolean b = i + 1 < text.length();
				int j = i + 1;
				if (b && Character.isLowerCase(text.charAt(j))) {
					result = "Found a match!";
					break;
				}
			}
		}
		return result;
	}
}
