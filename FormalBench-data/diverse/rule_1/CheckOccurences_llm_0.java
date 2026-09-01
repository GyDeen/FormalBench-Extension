
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckOccurences {

	public static HashMap<String, Integer> checkOccurences(int[][] array) {
		HashMap<String, Integer> test = new HashMap<>();

		for (int[] i : array) {
			Arrays.sort(i);
			String string = Arrays.toString(i);
			test.put(string, test.getOrDefault(string, 0) + 1);
		}

		return test;
	}
}
