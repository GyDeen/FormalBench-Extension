import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckEquality {

	public static String checkEquality(String string) {
		if (string.length() == 1) {
			return "Equal";
		}
		if (string.charAt(0) == string.charAt(string.length() - 1)) {
			return "Equal";
		} else {
			return "Not Equal";
		}
	}
}
