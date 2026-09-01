
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractNthElement {

	public static Object[] extractNthElement(Object[][] arr, int num) {
		int index = arr.length;
		Object[] objects = new Object[index];
		for (int j = 0; j < index; j++) {
			objects[j] = arr[j][num];
		}
		return objects;
	}
}
