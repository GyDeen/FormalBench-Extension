
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckDistinct {

	public static Boolean checkDistinct(int[] testTup) {
		Set<Integer> set = new HashSet<Integer>();
		int num = 0;
		while (num < testTup.length) {
			if (set.contains(testTup[num])) {
				return false;
			} else {
				set.add(testTup[num]);
			}
			num++;
		}
		return true;
	}
}
