
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractRear {

	public static String[] extractRear(String[] testTuple) {
		String[] result = new String[testTuple.length];
		int i = 0;
		while (i < testTuple.length) {
			result[i] = testTuple[i]
					.substring(testTuple[i].length() - 1);
			i++;
		}
		return result;
	}
}
