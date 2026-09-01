
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InsertElement {

	public static String[] insertElement(String[] array, String element) {
		int length = array.length;
		String[] result = new String[length * 2];

		int offset = 0;
		while (offset < length) {
			result[offset * 2] = element;
			result[offset * 2 + 1] = array[offset];
			offset++;
		}
		return result;
	}
}
