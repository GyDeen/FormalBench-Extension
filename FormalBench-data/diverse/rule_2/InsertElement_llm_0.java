
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InsertElement {

	public static String[] insertElement(String[] array, String element) {
		int length = array.length;
		String[] result = new String[length * 2];

		int index = 0;
		while (index < length) {
			result[index * 2] = element;
			result[index * 2 + 1] = array[index];
			index++;
		}
		return result;
	}
}
