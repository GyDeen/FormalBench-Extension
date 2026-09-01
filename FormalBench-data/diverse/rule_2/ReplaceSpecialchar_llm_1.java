import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReplaceSpecialchar {

	public static String replaceSpecialchar(String text) {
		String[] arr = text.split(",");
		int n = 0;
		while (n < arr.length) {
			arr[n] = arr[n].replaceAll(" ", ":");
			arr[n] = arr[n].replaceAll("\\.", ":");
			n++;
		}
		return String.join(":", arr);
	}
}
