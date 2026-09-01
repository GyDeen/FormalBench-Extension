import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckStr {

	public static String checkStr(String str) {
		String result = "^[aeiouAEIOU].*";
		return str.matches(result) ? "Valid" : "Invalid";
	}
}
