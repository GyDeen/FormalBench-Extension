
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SortCounter {

	public static Object[][] sortCounter(HashMap<String, Integer> dict1) {
		Object[][] dict = new Object[dict1.size()][2];
		int num = 0;

		for (Map.Entry<String, Integer> entries : dict1.entrySet()) {
			dict[num][0] = entries.getKey();
			dict[num][1] = entries.getValue();
			num++;
		}

		Arrays.sort(dict, (Sort, c) -> ((Integer) c[1])
				.compareTo((Integer) Sort[1]));

		return dict;
	}
}
