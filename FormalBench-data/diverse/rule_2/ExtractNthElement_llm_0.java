
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractNthElement {

	public static Object[] extractNthElement(Object[][] array, int n) {
		int length = array.length;
		Object[] result = new Object[length];
		int index = 0;
		while (index < length) {
			result[index] = array[index][n];
			index++;
		}
		return result;
	}
}
