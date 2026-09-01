
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class TwoUniqueNums {

	public static int[] twoUniqueNums(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for (int n : nums) {
			if (set.contains(n)) {
				set.remove(n);
			} else {
				set.add(n);
			}
		}
		int[] result = new int[set.size()];
		int index = 0;
		for (int num : set) {
			result[index] = num;
			index++;
		}
		return result;
	}
}
