
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseStringArray {

	public static String[] reverseStringArray(String[] stringArray) {
		String[] reversedArray = new String[stringArray.length];
		int index = 0;
		while (index < stringArray.length) {
			reversedArray[index] = new StringBuilder(stringArray[index]).reverse().toString();
			index++;
		}
		return reversedArray;
	}
}
