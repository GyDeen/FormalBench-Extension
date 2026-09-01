
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class StringToTuple {

	public static String[] stringToTuple(String Array) {
		int i = 0;
		for (char c : Array.toCharArray()) {
			if (c != ' ') {
				i++;
			}
		}

		String[] stringTuple = new String[i];
		int start = 0;

		for (char c : Array.toCharArray()) {
			if (c != ' ') {
				stringTuple[start++] = Character.toString(c);
			}
		}

		return stringTuple;
	}
}
