
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountVariable {

	public static String[] countVariable(int a, int b, int c, int d) {
		int totalSize = Math.max(0, a) + Math.max(0, b) + Math.max(0, c) + Math.max(0, d);
		String[] array = new String[totalSize];
		int index = 0;

		int current = 0;
		while (current < a) {
			array[index++] = "p";
			current++;
		}
		int current = 0;
		while (current < b) {
			array[index++] = "q";
			current++;
		}
		int current = 0;
		while (current < c) {
			array[index++] = "r";
			current++;
		}
		int current = 0;
		while (current < d) {
			array[index++] = "s";
			current++;
		}

		return array;
	}
}
