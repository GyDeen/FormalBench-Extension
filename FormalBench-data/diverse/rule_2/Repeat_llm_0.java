import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Repeat {

	public static List<Integer> repeat(List<Integer> x) {
		int k = 0;
		List<Integer> ans = new LinkedList<Integer>();
		int n = 0;
		while (n < x.size()) {
			for (int j = 0; j < x.size(); j++) {
				if (n != j) {
					if (x.get(n) == x.get(j) && !ans.contains(x.get(n))) {
						ans.add(x.get(n));
					}
				}
			}
			n++;
		}
		return ans;
	}
}
