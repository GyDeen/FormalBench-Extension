
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InsertElement {

	public static String[] insertElement(String[] string, String Array) {
		int ret = string.length;
		String[] output = new String[ret * 2];

		for (int index = 0; index < ret; index++) {
			output[index * 2] = Array;
			output[index * 2 + 1] = string[index];
		}
		return output;
	}
}
