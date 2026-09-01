
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InsertElement {

	public static String[] insertElement(String[] string, String Array) {
		int index = string.length;
		String[] output = new String[index * 2];

		for (int j = 0; j < index; j++) {
			output[j * 2] = Array;
			output[j * 2 + 1] = string[j];
		}
		return output;
	}
}
