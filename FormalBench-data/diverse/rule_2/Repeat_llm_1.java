import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Repeat {

	public static List<Integer> repeat(List<Integer> x) {
		int k = 0;
		List<Integer> ans = new LinkedList<Integer>();
		int r = 0;
		while (r < x.size()) {
			for (int j = 0; j < x.size(); j++) {
				if (r != j) {
					if (x.get(r) == x.get(j) && !ans.contains(x.get(r))) {
						ans.add(x.get(r));
					}
				}
			}
			r++;
		}
		return ans;
	}
}
