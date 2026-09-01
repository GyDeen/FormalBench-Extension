
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class UniqueProduct {

	public static int uniqueProduct(int[] arr) {
		Set<Integer> sets = new HashSet<>();
		int array = 1;
		boolean b = false;

		for (int i : arr) {
			if (sets.add(i)) {
				array *= i;
				b = true;
			}
		}

		return b ? array : 0;
	}
}
