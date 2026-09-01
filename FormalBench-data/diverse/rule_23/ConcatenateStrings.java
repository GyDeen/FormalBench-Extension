import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateStrings {

	public static List<String> concatenateStrings(List<String> testTup1, List<String> testTup2) {
		List<String> result = new ArrayList<>();
		for (int i = 0; testTup1.size() > i; i++) {
			result.add(testTup1.get(i) + testTup2.get(i));
		}
		return result;
	}
}
