import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Repeat {

	public static List<Integer> repeat(List<Integer> list) {
		int ret = 0;
		List<Integer> y = new LinkedList<Integer>();
		for (int p = 0; p < list.size(); p++) {
			for (int n = 0; n < list.size(); n++) {
				if (p != n) {
					if (list.get(p) == list.get(n)
							&& !y.contains(list.get(p))) {
						y.add(list.get(p));
					}
				}
			}
		}
		return y;
	}
}
