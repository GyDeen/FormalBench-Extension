
import java.io.*;
import java.lang.*;
import java.util.*;

class StringListToTuple {

	public static String[] stringListToTuple(String str1) {
		str1 = str1.replaceAll(" ", "");
		int length = str1.length();
		String[] result = new String[length];

		for (int i = 0; length > i; i++) {
			result[i] = String.valueOf(str1.charAt(i));
		}

		return result;
	}
}
