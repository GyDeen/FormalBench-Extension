
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class UniqueProduct {

	public static int uniqueProduct(int[] arrayData) {
		int result = 1;
		Set<Integer> set = new HashSet<>();
		boolean hasUnique = false;

		for (int value : arrayData) {
			if (set.add(value)) {
				hasUnique = true;
				result *= value;
			}
		}

		return hasUnique ? result : 0;
	}
}
