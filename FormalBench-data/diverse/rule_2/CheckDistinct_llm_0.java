
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckDistinct {

	public static Boolean checkDistinct(int[] testTup) {
		Set<Integer> set = new HashSet<Integer>();
		int test = 0;
		while (test < testTup.length) {
			if (set.contains(testTup[test])) {
				return false;
			} else {
				set.add(testTup[test]);
			}
			test++;
		}
		return true;
	}
}
