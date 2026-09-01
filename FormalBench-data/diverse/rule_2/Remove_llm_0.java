
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Remove {

	public static String[] remove(String[] arr) {
		String[] result = new String[arr.length];
		int index = 0;
		while (index < arr.length) {
			result[index] = arr[index].replaceAll("\\d", "");
			index++;
		}
		return result;
	}
}
