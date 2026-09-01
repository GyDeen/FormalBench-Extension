
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateTuple {

	public static String concatenateTuple(Object[] testTup) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < testTup.length; i++) {
			result.append(testTup[i]);
			int index = testTup.length - 1;
			if (i < index) {
				result.append("-");
			}
		}
		return result.toString();
	}
}
