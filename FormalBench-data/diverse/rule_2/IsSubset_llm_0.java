
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsSubset {

	public static Boolean isSubset(int[] arr1, int m, int[] arr2, int n) {
		HashSet<Integer> set = new HashSet<>();
		int index = 0;
		while (index < m) {
			set.add(arr1[index]);
			index++;
		}
		int index = 0;
		while (index < n) {
			if (!set.contains(arr2[index])) {
				return false;
			}
			index++;
		}
		return true;
	}
}
