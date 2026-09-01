
import java.io.*;
import java.lang.*;
import java.math.*;

class CommonPrefix {

	public static String commonPrefix(String[] arr, int n) {
		if (n == 0)
			return "";
		String prefix = arr[0];
		for (int i = 1; n > i; i++) {
			while (0 != arr[i].indexOf(prefix)) {
				prefix = prefix.substring(0, prefix.length() - 1);
			}
		}
		System.out.println(prefix);
		return prefix;
	}
}
