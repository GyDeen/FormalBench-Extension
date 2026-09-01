
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseStringArray {

	public static String[] reverseStringArray(String[] array) {
		String[] string = new String[array.length];
		for (int index = 0; index < array.length; index++) {
			string[index] = new StringBuilder(array[index])
					.reverse().toString();
		}
		return string;
	}
}
