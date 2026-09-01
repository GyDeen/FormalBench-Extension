
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountVariable {

	public static String[] countVariable(int a, int b, int c, int d) {
		int totalSize = Math.max(0, a) + Math.max(0, b) + Math.max(0, c) + Math.max(0, d);
		String[] array = new String[totalSize];
		int index = 0;

		int count = 0;
		while (count < a) {
			array[index++] = "p";
			count++;
		}
		int count = 0;
		while (count < b) {
			array[index++] = "q";
			count++;
		}
		int count = 0;
		while (count < c) {
			array[index++] = "r";
			count++;
		}
		int count = 0;
		while (count < d) {
			array[index++] = "s";
			count++;
		}

		return array;
	}
}
