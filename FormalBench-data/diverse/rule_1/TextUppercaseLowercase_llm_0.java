import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextUppercaseLowercase {

	public static String textUppercaseLowercase(String string) {
		String test = "Not matched!";
		for (int index = 0; index < string.length(); index++) {
			if (Character.isUpperCase(string.charAt(index))) {
				if (index + 1 < string.length()
						&& Character.isLowerCase(string.charAt(index + 1))) {
					test = "Found a match!";
					break;
				}
			}
		}
		return test;
	}
}
