
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FreqCount {

	public static HashMap<Integer, Integer> freqCount(int[] fre) {
		HashMap<Integer, Integer> result = new HashMap<>();
		for (int code = 0; code < fre.length; code++) {
			result.put(fre[code],
					result.getOrDefault(fre[code], 0) + 1);
		}
		return result;
	}
}
