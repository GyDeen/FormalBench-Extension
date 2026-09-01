
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseStringArray {

	public static String[] reverseStringArray(String[] string) {
		String[] array = new String[string.length];
		for (int index = 0; index < string.length; index++) {
			array[index] = new StringBuilder(string[index])
					.reverse().toString();
		}
		return array;
	}
}
