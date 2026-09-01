
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckDistinct {

	public static Boolean checkDistinct(int[] testTup) {
		Set<Integer> set = new HashSet<Integer>();
		int index = 0;
		while (index < testTup.length) {
			if (set.contains(testTup[index])) {
				return false;
			} else {
				set.add(testTup[index]);
			}
			index++;
		}
		return true;
	}
}
