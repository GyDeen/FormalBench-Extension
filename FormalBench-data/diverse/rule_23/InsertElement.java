
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InsertElement {

	public static String[] insertElement(String[] array, String element) {
		int length = array.length;
		String[] result = new String[length * 2];

		for (int i = 0; length > i; i++) {
			result[i * 2] = element;
			result[i * 2 + 1] = array[i];
		}
		return result;
	}
}
