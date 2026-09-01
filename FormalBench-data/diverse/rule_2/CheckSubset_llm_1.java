
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckSubset {

	public static Boolean checkSubset(int[] testTup1, int[] testTup2) {
		Set<Integer> set = new HashSet<>();
		int num = 0;
		while (num < testTup1.length) {
			set.add(testTup1[num]);
			num++;
		}
		int num = 0;
		while (num < testTup2.length) {
			if (!set.contains(testTup2[num])) {
				return false;
			}
			num++;
		}
		return true;
	}
}
