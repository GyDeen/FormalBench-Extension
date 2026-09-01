import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckEquality {

	public static String checkEquality(String str) {
		if (1 == str.length()) {
			return "Equal";
		}
		if (str.charAt(str.length() - 1) == str.charAt(0)) {
			return "Equal";
		} else {
			return "Not Equal";
		}
	}
}
