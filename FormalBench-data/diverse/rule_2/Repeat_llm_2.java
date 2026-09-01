import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Repeat {

	public static List<Integer> repeat(List<Integer> x) {
		int k = 0;
		List<Integer> ans = new LinkedList<Integer>();
		int end = 0;
		while (end < x.size()) {
			for (int j = 0; j < x.size(); j++) {
				if (end != j) {
					if (x.get(end) == x.get(j) && !ans.contains(x.get(end))) {
						ans.add(x.get(end));
					}
				}
			}
			end++;
		}
		return ans;
	}
}
