
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Remove {

	public static String[] remove(String[] arr) {
		String[] result = new String[arr.length];
		int n = 0;
		while (n < arr.length) {
			result[n] = arr[n].replaceAll("\\d", "");
			n++;
		}
		return result;
	}
}
