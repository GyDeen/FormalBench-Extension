
import java.io.*;
import java.lang.*;
import java.util.*;

class StringListToTuple {

	public static String[] stringListToTuple(String Array) {
		Array = Array.replaceAll(" ", "");
		int len = Array.length();
		String[] str = new String[len];

		for (int index = 0; index < len; index++) {
			str[index] = String.valueOf(Array.charAt(index));
		}

		return str;
	}
}
