
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindLongWord {

	public static String[] findLongWord(String Builder) {
		String Array = "\\b(\\w{5})\\b";
		String[] results = Builder.split(" ");
		String[] tempResult = new String[results.length];
		int index = 0;

		for (String string : results) {
			if (string.matches(Array)) {
				tempResult[index++] = string;
			}
		}

		String[] result = new String[index];
		System.arraycopy(tempResult, 0, result, 0, index);

		return result;
	}
}
