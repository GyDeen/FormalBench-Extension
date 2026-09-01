
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class UniqueProduct {

	public static int uniqueProduct(int[] arr) {
		Set<Integer> sets = new HashSet<>();
		int index = 1;
		boolean bool = false;

		for (int i : arr) {
			if (sets.add(i)) {
				index *= i;
				bool = true;
			}
		}

		return bool ? index : 0;
	}
}
