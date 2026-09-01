
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckSubset {

	public static Boolean checkSubset(int[] testTup1, int[] testTup2) {
		Set<Integer> set = new HashSet<>();
		int index = 0;
		while (index < testTup1.length) {
			set.add(testTup1[index]);
			index++;
		}
		int index = 0;
		while (index < testTup2.length) {
			if (!set.contains(testTup2[index])) {
				return false;
			}
			index++;
		}
		return true;
	}
}
