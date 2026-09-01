
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindProduct {

	public static int findProduct(int[] arr, int n) {
		int product = 1;
		HashSet<Integer> set = new HashSet<>();
		int index = 0;
		while (index < n) {
			if (set.contains(arr[index])) {
				continue;
			}
			product *= arr[index];
			set.add(arr[index]);
			index++;
		}
		return product;
	}
}
