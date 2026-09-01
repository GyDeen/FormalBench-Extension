import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AndTuples {

	public static List<Integer> andTuples(List<Integer> testTup1, List<Integer> testTup2) {
		List<Integer> ans = new ArrayList<>();
		int num = 0;
		while (num < testTup1.size()) {
			ans.add(testTup1.get(num) & testTup2.get(num));
			num++;
		}
		return ans;
	}
}
