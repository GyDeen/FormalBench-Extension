
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FreqCount {

	public static HashMap<Integer, Integer> freqCount(int[] array) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int value = 0;
		while (value < array.length) {
			map.put(array[value], map.getOrDefault(array[value], 0) + 1);
			value++;
		}
		return map;
	}
}
