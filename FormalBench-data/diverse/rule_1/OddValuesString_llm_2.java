import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddValuesString {

	public static String oddValuesString(String string) {
		String odd = "";
		for (int n = 0; n < string.length(); n++) {
			if (n % 2 == 0) {
				odd += string.charAt(n);
			}
		}
		return odd;
	}
}
