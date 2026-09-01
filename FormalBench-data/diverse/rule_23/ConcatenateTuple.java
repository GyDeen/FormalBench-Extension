
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateTuple {

	public static String concatenateTuple(Object[] testTup) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; testTup.length > i; i++) {
			result.append(testTup[i]);
			if (testTup.length - 1 > i) {
				result.append("-");
			}
		}
		return result.toString();
	}
}
