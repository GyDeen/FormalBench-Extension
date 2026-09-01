
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConcatenateTuple {

	public static String concatenateTuple(Object[] testTup) {
		StringBuilder result = new StringBuilder();
		int indent = 0;
		while (indent < testTup.length) {
			result.append(testTup[indent]);
			if (indent < testTup.length - 1) {
				result.append("-");
			}
			indent++;
		}
		return result.toString();
	}
}
