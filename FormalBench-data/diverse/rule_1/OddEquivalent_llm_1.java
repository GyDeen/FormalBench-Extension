import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddEquivalent {

	public static int oddEquivalent(String string, int b) {
		int result = 0;
		for (int c = 0; c < string.length(); c++) {
			if (string.charAt(c) == '0') {
				result++;
			} else {
				result = (result + 2) % b;
			}
		}
		return result;
	}
}
