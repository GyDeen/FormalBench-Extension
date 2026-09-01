
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Remove {

	public static String[] remove(String[] arr) {
		String[] result = new String[arr.length];
		int i = 0;
		while (i < arr.length) {
			result[i] = arr[i].replaceAll("\\d", "");
			i++;
		}
		return result;
	}
}
