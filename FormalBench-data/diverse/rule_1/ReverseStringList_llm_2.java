
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseStringArray {

	public static String[] reverseStringArray(String[] strings) {
		String[] string = new String[strings.length];
		for (int index = 0; index < strings.length; index++) {
			string[index] = new StringBuilder(strings[index])
					.reverse().toString();
		}
		return string;
	}
}
