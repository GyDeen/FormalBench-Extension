import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TextUppercaseLowercase {

	public static String textUppercaseLowercase(String input) {
		String test = "Not matched!";
		for (int index = 0; index < input.length(); index++) {
			if (Character.isUpperCase(input.charAt(index))) {
				if (index + 1 < input.length()
						&& Character.isLowerCase(input.charAt(index + 1))) {
					test = "Found a match!";
					break;
				}
			}
		}
		return test;
	}
}
