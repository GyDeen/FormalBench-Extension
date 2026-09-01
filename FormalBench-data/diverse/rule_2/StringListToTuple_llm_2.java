
import java.io.*;
import java.lang.*;
import java.util.*;

class StringListToTuple {

	public static String[] stringListToTuple(String str1) {
		str1 = str1.replaceAll(" ", "");
		int length = str1.length();
		String[] result = new String[length];

		int i = 0;
		while (i < length) {
			result[i] = String.valueOf(str1.charAt(i));
			i++;
		}

		return result;
	}
}
