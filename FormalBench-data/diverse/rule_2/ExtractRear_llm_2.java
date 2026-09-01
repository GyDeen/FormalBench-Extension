
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractRear {

	public static String[] extractRear(String[] testTuple) {
		String[] result = new String[testTuple.length];
		int index = 0;
		while (index < testTuple.length) {
			result[index] = testTuple[index]
					.substring(testTuple[index].length() - 1);
			index++;
		}
		return result;
	}
}
