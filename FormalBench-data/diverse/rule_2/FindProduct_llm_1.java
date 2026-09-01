
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindProduct {

	public static int findProduct(int[] arr, int n) {
		int product = 1;
		HashSet<Integer> set = new HashSet<>();
		int prod = 0;
		while (prod < n) {
			if (set.contains(arr[prod])) {
				continue;
			}
			product *= arr[prod];
			set.add(arr[prod]);
			prod++;
		}
		return product;
	}
}
