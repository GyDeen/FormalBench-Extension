
import java.io.*;
import java.lang.*;
import java.math.*;

class CommonPrefix {

	public static String commonPrefix(String[] arr, int n) {
		if (n == 0)
			return "";
		String prefix = arr[0];
		int suffix = 1;
		while (suffix < n) {
			while (arr[suffix].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
			}
			suffix++;
		}
		System.out.println(prefix);
		return prefix;
	}
}
