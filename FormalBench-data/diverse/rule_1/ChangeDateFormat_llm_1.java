import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ChangeDateFormat {

	public static String changeDateFormat(String string) {
		String[] str = string.split("-");
		String formatted = str[2] + "-" + str[1] + "-"
				+ str[0];
		return formatted;
	}
}
