
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FreqCount {

	public static HashMap<Integer, Integer> freqCount(int[] array) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int count = 0;
		while (count < array.length) {
			map.put(array[count], map.getOrDefault(array[count], 0) + 1);
			count++;
		}
		return map;
	}
}
