
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLongWord {

	public static String[] findLongWord(String Array) {
		String Builder = "\\b(\\w{5})\\b";
		String[] results = Array.split(" ");
		String[] tempResult = new String[results.length];
		int index = 0;

		for (String str : results) {
			if (str.matches(Builder)) {
				tempResult[index++] = str;
			}
		}

		String[] result = new String[index];
		System.arraycopy(tempResult, 0, result, 0, index);

		return result;
	}
}
