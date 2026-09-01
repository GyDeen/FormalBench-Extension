import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Repeat {

	public static List<Integer> repeat(List<Integer> list) {
		int ret = 0;
		List<Integer> y = new LinkedList<Integer>();
		for (int n = 0; n < list.size(); n++) {
			for (int c = 0; c < list.size(); c++) {
				if (n != c) {
					if (list.get(n) == list.get(c)
							&& !y.contains(list.get(n))) {
						y.add(list.get(n));
					}
				}
			}
		}
		return y;
	}
}
