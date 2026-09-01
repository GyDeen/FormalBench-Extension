import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddValuesString {

	public static String oddValuesString(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			int tmp = i % 2;
			if (tmp == 0) {
				result += str.charAt(i);
			}
		}
		return result;
	}
}
