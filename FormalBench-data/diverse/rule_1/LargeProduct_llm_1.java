import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargeProduct {

	public static List<Integer> largeProduct(List<Integer> input, List<Integer> others,
			int n) {
		List<Integer> list = new ArrayList<>();
		for (int k = 0; k < input.size(); k++) {
			for (int c = 0; c < others.size(); c++) {
				list
						.add(input.get(k) * others.get(c));
			}
		}
		Collections.sort(list, Collections.reverseOrder());
		return list.subList(0, n);
	}
}
