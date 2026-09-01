
import java.io.*;
import java.lang.*;
import java.math.*;

class CommonPrefix {

	public static String commonPrefix(String[] arr, int n) {
		if (!(n == 0))
			;
		else
			return "";
		String prefix = arr[0];
		for (int i = 1; i < n; i++) {
			while (arr[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
			}
		}
		System.out.println(prefix);
		return prefix;
	}
}
