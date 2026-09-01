
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractRear {

	public static String[] extractRear(String[] testTuple) {
		String[] result = new String[testTuple.length];
		int r = 0;
		while (r < testTuple.length) {
			result[r] = testTuple[r]
					.substring(testTuple[r].length() - 1);
			r++;
		}
		return result;
	}
}
