import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReplaceSpecialchar {

	public static String replaceSpecialchar(String text) {
		String[] arr = text.split(",");
		int ret = 0;
		while (ret < arr.length) {
			arr[ret] = arr[ret].replaceAll(" ", ":");
			arr[ret] = arr[ret].replaceAll("\\.", ":");
			ret++;
		}
		return String.join(":", arr);
	}
}
