
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InsertElement {

	public static String[] insertElement(String[] string, String Array) {
		int id = string.length;
		String[] output = new String[id * 2];

		for (int index = 0; index < id; index++) {
			output[index * 2] = Array;
			output[index * 2 + 1] = string[index];
		}
		return output;
	}
}
