
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortCounter {

	public static Object[][] sortCounter(HashMap<String, Integer> dict1) {
		Object[][] dict = new Object[dict1.size()][2];
		int count = 0;

		for (Map.Entry<String, Integer> entries : dict1.entrySet()) {
			dict[count][0] = entries.getKey();
			dict[count][1] = entries.getValue();
			count++;
		}

		Arrays.sort(dict, (Sort, c) -> ((Integer) c[1])
				.compareTo((Integer) Sort[1]));

		return dict;
	}
}
