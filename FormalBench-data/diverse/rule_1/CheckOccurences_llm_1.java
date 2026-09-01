
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckOccurences {

	public static HashMap<String, Integer> checkOccurences(int[][] array) {
		HashMap<String, Integer> map = new HashMap<>();

		for (int[] test : array) {
			Arrays.sort(test);
			String string = Arrays.toString(test);
			map.put(string, map.getOrDefault(string, 0) + 1);
		}

		return map;
	}
}
