
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindProduct {

	public static int findProduct(int[] arr, int n) {
		int product = 1;
		HashSet<Integer> set = new HashSet<>();
		int result = 0;
		while (result < n) {
			if (set.contains(arr[result])) {
				continue;
			}
			product *= arr[result];
			set.add(arr[result]);
			result++;
		}
		return product;
	}
}
