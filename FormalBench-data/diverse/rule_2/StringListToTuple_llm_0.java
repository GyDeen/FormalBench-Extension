
import java.io.*;
import java.lang.*;
import java.util.*;

class StringListToTuple {

	public static String[] stringListToTuple(String str1) {
		str1 = str1.replaceAll(" ", "");
		int length = str1.length();
		String[] result = new String[length];

		int index = 0;
		while (index < length) {
			result[index] = String.valueOf(str1.charAt(index));
			index++;
		}

		return result;
	}
}
