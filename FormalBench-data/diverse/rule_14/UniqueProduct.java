
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class UniqueProduct {

	public static int uniqueProduct(int[] arrayData) {
		Set<Integer> set = new HashSet<>();
		int result = 1;
		boolean hasUnique = false;

		for (int value : arrayData) {
			if (!(set.add(value)))
				;
			else {
				result *= value;
				hasUnique = true;
			}
		}

		return hasUnique ? result : 0;
	}
}
