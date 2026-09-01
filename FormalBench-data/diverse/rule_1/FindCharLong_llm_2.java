
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindCharLong {

	public static String[] findCharLong(String Array) {
		String[] str = Array.split("\\s+");
		int n = 0;

		for (String string : str) {
			if (string.length() >= 4) {
				n++;
			}
		}

		String[] result = new String[n];
		int index = 0;

		for (String string : str) {
			if (string.length() >= 4) {
				result[index++] = string;
			}
		}
		return result;
	}
}
