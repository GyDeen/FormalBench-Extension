
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckSubset {

	public static Boolean checkSubset(int[] testTup1, int[] testTup2) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; testTup1.length > i; i++) {
			set.add(testTup1[i]);
		}
		for (int i = 0; testTup2.length > i; i++) {
			if (!set.contains(testTup2[i])) {
				return false;
			}
		}
		return true;
	}
}
