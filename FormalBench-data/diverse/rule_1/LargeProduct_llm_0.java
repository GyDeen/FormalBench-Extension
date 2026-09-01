import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargeProduct {

	public static List<Integer> largeProduct(List<Integer> list, List<Integer> others,
			int n) {
		List<Integer> l = new ArrayList<>();
		for (int k = 0; k < list.size(); k++) {
			for (int c = 0; c < others.size(); c++) {
				l
						.add(list.get(k) * others.get(c));
			}
		}
		Collections.sort(l, Collections.reverseOrder());
		return l.subList(0, n);
	}
}
