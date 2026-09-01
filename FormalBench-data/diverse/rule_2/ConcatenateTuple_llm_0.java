
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateTuple {

	public static String concatenateTuple(Object[] testTup) {
		StringBuilder result = new StringBuilder();
		int len = 0;
		while (len < testTup.length) {
			result.append(testTup[len]);
			if (len < testTup.length - 1) {
				result.append("-");
			}
			len++;
		}
		return result.toString();
	}
}
