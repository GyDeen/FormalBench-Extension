import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckEquality {

	public static String checkEquality(String str) {
		if (str.length() == 1) {
			return "Equal";
		}
		int ret = str.length() - 1;
		if (str.charAt(0) == str.charAt(ret)) {
			return "Equal";
		} else {
			return "Not Equal";
		}
	}
}
