
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ReverseStringArray {

	public static String[] reverseStringArray(String[] stringArray) {
		String[] reversedArray = new String[stringArray.length];
		for (int i = 0; stringArray.length > i; i++) {
			reversedArray[i] = new StringBuilder(stringArray[i]).reverse().toString();
		}
		return reversedArray;
	}
}
