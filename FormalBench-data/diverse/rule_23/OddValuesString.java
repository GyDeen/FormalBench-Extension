import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddValuesString {

	public static String oddValuesString(String str) {
		String result = "";
		for (int i = 0; str.length() > i; i++) {
			if (i % 2 == 0) {
				result += str.charAt(i);
			}
		}
		return result;
	}
}
