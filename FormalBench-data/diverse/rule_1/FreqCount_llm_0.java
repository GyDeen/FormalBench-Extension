
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FreqCount {

	public static HashMap<Integer, Integer> freqCount(int[] fre) {
		HashMap<Integer, Integer> result = new HashMap<>();
		for (int index = 0; index < fre.length; index++) {
			result.put(fre[index],
					result.getOrDefault(fre[index], 0) + 1);
		}
		return result;
	}
}
