import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateStrings {

	public static List<String> concatenateStrings(List<String> testTup1, List<String> testTup2) {
		List<String> result = new ArrayList<>();
		int len = 0;
		while (len < testTup1.size()) {
			result.add(testTup1.get(len) + testTup2.get(len));
			len++;
		}
		return result;
	}
}
