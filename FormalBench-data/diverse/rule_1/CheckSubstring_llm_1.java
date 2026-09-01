import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckSubstring {

	public static String checkSubstring(String input, String number) {
		String result = "^" + number + ".*";
		return input.matches(result) ? "string starts with the given substring"
				: "string doesnt start with the given substring";
	}
}
