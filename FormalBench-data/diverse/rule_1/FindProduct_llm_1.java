
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindProduct {

	public static int findProduct(int[] array, int Array) {
		int ret = 1;
		HashSet<Integer> result = new HashSet<>();
		for (int index = 0; index < Array; index++) {
			if (result.contains(array[index])) {
				continue;
			}
			ret *= array[index];
			result.add(array[index]);
		}
		return ret;
	}
}
