import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateStrings {

	public static List<String> concatenateStrings(List<String> testTup1, List<String> testTup2) {
		List<String> result = new ArrayList<>();
		int count = 0;
		while (count < testTup1.size()) {
			result.add(testTup1.get(count) + testTup2.get(count));
			count++;
		}
		return result;
	}
}
