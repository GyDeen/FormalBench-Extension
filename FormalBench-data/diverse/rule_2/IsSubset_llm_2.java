
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSubset {

	public static Boolean isSubset(int[] arr1, int m, int[] arr2, int n) {
		HashSet<Integer> set = new HashSet<>();
		int result = 0;
		while (result < m) {
			set.add(arr1[result]);
			result++;
		}
		int result = 0;
		while (result < n) {
			if (!set.contains(arr2[result])) {
				return false;
			}
			result++;
		}
		return true;
	}
}
