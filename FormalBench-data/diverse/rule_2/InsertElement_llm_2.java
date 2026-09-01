
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InsertElement {

	public static String[] insertElement(String[] array, String element) {
		int length = array.length;
		String[] result = new String[length * 2];

		int start = 0;
		while (start < length) {
			result[start * 2] = element;
			result[start * 2 + 1] = array[start];
			start++;
		}
		return result;
	}
}
