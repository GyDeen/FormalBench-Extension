
import java.io.*;
import java.lang.*;
import java.util.*;

class StringListToTuple {

	public static String[] stringListToTuple(String List) {
		List = List.replaceAll(" ", "");
		int len = List.length();
		String[] arr = new String[len];

		for (int index = 0; index < len; index++) {
			arr[index] = String.valueOf(List.charAt(index));
		}

		return arr;
	}
}
