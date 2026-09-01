
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseStringArray {

	public static String[] reverseStringArray(String[] stringArray) {
		String[] reversedArray = new String[stringArray.length];
		int i = 0;
		while (i < stringArray.length) {
			reversedArray[i] = new StringBuilder(stringArray[i]).reverse().toString();
			i++;
		}
		return reversedArray;
	}
}
