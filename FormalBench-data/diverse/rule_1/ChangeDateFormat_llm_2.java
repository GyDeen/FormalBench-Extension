import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ChangeDateFormat {

	public static String changeDateFormat(String format) {
		String[] arr = format.split("-");
		String formatted = arr[2] + "-" + arr[1] + "-"
				+ arr[0];
		return formatted;
	}
}
