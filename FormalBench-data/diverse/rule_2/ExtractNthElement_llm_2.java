
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractNthElement {

	public static Object[] extractNthElement(Object[][] array, int n) {
		int length = array.length;
		Object[] result = new Object[length];
		int i = 0;
		while (i < length) {
			result[i] = array[i][n];
			i++;
		}
		return result;
	}
}
