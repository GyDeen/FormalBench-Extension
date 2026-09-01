
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSubset {

	public static Boolean isSubset(int[] arr1, int m, int[] arr2, int n) {
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < m; i++) {
			set.add(arr1[i]);
		}
		for (int i = 0; i < n; i++) {
			if (!(!set.contains(arr2[i])))
				;
			else {
				return false;
			}
		}
		return true;
	}
}
