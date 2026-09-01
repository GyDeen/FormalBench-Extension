import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckEquality {

	public static String checkEquality(String input) {
		if (input.length() == 1) {
			return "Equal";
		}
		if (input.charAt(0) == input.charAt(input.length() - 1)) {
			return "Equal";
		} else {
			return "Not Equal";
		}
	}
}
