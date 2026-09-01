
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractNthElement {

	public static Object[] extractNthElement(Object[][] array, int n) {
		int length = array.length;
		Object[] result = new Object[length];
		int start = 0;
		while (start < length) {
			result[start] = array[start][n];
			start++;
		}
		return result;
	}
}
