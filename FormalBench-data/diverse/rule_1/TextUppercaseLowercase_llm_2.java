import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextUppercaseLowercase {

	public static String textUppercaseLowercase(String string) {
		String test = "Not matched!";
		for (int count = 0; count < string.length(); count++) {
			if (Character.isUpperCase(string.charAt(count))) {
				if (count + 1 < string.length()
						&& Character.isLowerCase(string.charAt(count + 1))) {
					test = "Found a match!";
					break;
				}
			}
		}
		return test;
	}
}
