import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddEquivalent {

	public static int oddEquivalent(String string, int c) {
		int result = 0;
		for (int j = 0; j < string.length(); j++) {
			if (string.charAt(j) == '0') {
				result++;
			} else {
				result = (result + 2) % c;
			}
		}
		return result;
	}
}
