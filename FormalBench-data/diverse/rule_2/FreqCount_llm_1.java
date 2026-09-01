
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FreqCount {

	public static HashMap<Integer, Integer> freqCount(int[] array) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int result = 0;
		while (result < array.length) {
			map.put(array[result], map.getOrDefault(array[result], 0) + 1);
			result++;
		}
		return map;
	}
}
