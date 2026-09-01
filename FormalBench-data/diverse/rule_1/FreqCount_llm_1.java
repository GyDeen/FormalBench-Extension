
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FreqCount {

	public static HashMap<Integer, Integer> freqCount(int[] fre) {
		HashMap<Integer, Integer> initialize = new HashMap<>();
		for (int index = 0; index < fre.length; index++) {
			initialize.put(fre[index],
					initialize.getOrDefault(fre[index], 0) + 1);
		}
		return initialize;
	}
}
