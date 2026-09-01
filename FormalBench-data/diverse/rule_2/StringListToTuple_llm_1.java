
import java.io.*;
import java.lang.*;
import java.util.*;

class StringListToTuple {

	public static String[] stringListToTuple(String str1) {
		str1 = str1.replaceAll(" ", "");
		int length = str1.length();
		String[] result = new String[length];

		int offset = 0;
		while (offset < length) {
			result[offset] = String.valueOf(str1.charAt(offset));
			offset++;
		}

		return result;
	}
}
