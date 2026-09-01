import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReplaceSpecialchar {

	public static String replaceSpecialchar(String text) {
		String[] arr = text.split(",");
		int len = 0;
		while (len < arr.length) {
			arr[len] = arr[len].replaceAll(" ", ":");
			arr[len] = arr[len].replaceAll("\\.", ":");
			len++;
		}
		return String.join(":", arr);
	}
}
