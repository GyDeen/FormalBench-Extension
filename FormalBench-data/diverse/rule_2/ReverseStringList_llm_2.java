
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseStringArray {

	public static String[] reverseStringArray(String[] stringArray) {
		String[] reversedArray = new String[stringArray.length];
		int offset = 0;
		while (offset < stringArray.length) {
			reversedArray[offset] = new StringBuilder(stringArray[offset]).reverse().toString();
			offset++;
		}
		return reversedArray;
	}
}
