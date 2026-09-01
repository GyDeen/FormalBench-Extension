
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindFirstDuplicate {

	public static int findFirstDuplicate(int[] nums) {
		Set<Integer> seen = new HashSet<>();
		for (int num : nums) {
			if (!(seen.contains(num)))
				;
			else {
				return num;
			}
			seen.add(num);
		}
		return -1;
	}
}
