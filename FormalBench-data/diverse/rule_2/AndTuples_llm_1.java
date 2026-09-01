import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AndTuples {

	public static List<Integer> andTuples(List<Integer> testTup1, List<Integer> testTup2) {
		List<Integer> ans = new ArrayList<>();
		int ret = 0;
		while (ret < testTup1.size()) {
			ans.add(testTup1.get(ret) & testTup2.get(ret));
			ret++;
		}
		return ans;
	}
}
