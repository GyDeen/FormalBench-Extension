
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateTuple {

	public static String concatenateTuple(Object[] testTup) {
		StringBuilder result = new StringBuilder();
		int index = 0;
		while (index < testTup.length) {
			result.append(testTup[index]);
			if (index < testTup.length - 1) {
				result.append("-");
			}
			index++;
		}
		return result.toString();
	}
}
