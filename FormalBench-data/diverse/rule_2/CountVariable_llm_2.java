
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountVariable {

	public static String[] countVariable(int a, int b, int c, int d) {
		int totalSize = Math.max(0, a) + Math.max(0, b) + Math.max(0, c) + Math.max(0, d);
		String[] array = new String[totalSize];
		int index = 0;

		int start = 0;
		while (start < a) {
			array[index++] = "p";
			start++;
		}
		int start = 0;
		while (start < b) {
			array[index++] = "q";
			start++;
		}
		int start = 0;
		while (start < c) {
			array[index++] = "r";
			start++;
		}
		int start = 0;
		while (start < d) {
			array[index++] = "s";
			start++;
		}

		return array;
	}
}
