
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindProduct {

	public static int findProduct(int[] arr, int n) {
		HashSet<Integer> set = new HashSet<>();
		int product = 1;
		for (int i = 0; i < n; i++) {
			if (set.contains(arr[i])) {
				continue;
			}
			product *= arr[i];
			set.add(arr[i]);
		}
		return product;
	}
}
