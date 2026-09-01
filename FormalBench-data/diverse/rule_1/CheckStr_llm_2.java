import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckStr {

	public static String checkStr(String input) {
		String check = "^[aeiouAEIOU].*";
		return input.matches(check) ? "Valid" : "Invalid";
	}
}
