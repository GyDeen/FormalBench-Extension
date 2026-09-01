
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckSubset {

	public static Boolean checkSubset(int[] testTup1, int[] testTup2) {
		Set<Integer> set = new HashSet<>();
		int test = 0;
		while (test < testTup1.length) {
			set.add(testTup1[test]);
			test++;
		}
		int test = 0;
		while (test < testTup2.length) {
			if (!set.contains(testTup2[test])) {
				return false;
			}
			test++;
		}
		return true;
	}
}
