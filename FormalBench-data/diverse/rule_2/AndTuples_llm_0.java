import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AndTuples {

	public static List<Integer> andTuples(List<Integer> testTup1, List<Integer> testTup2) {
		List<Integer> ans = new ArrayList<>();
		int result = 0;
		while (result < testTup1.size()) {
			ans.add(testTup1.get(result) & testTup2.get(result));
			result++;
		}
		return ans;
	}
}
