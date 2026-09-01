
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSubset {

	public static Boolean isSubset(int[] arr1, int m, int[] arr2, int n) {
		HashSet<Integer> set = new HashSet<>();
		int i = 0;
		while (i < m) {
			set.add(arr1[i]);
			i++;
		}
		int i = 0;
		while (i < n) {
			if (!set.contains(arr2[i])) {
				return false;
			}
			i++;
		}
		return true;
	}
}
