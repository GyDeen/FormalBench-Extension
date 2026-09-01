import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateStrings {

	public static List<String> concatenateStrings(List<String> testTup1, List<String> testTup2) {
		List<String> result = new ArrayList<>();
		int n = 0;
		while (n < testTup1.size()) {
			result.add(testTup1.get(n) + testTup2.get(n));
			n++;
		}
		return result;
	}
}
