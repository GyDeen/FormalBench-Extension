
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindProduct {

	public static int findProduct(int[] args, int array) {
		int result = 1;
		HashSet<Integer> sets = new HashSet<>();
		for (int index = 0; index < array; index++) {
			if (sets.contains(args[index])) {
				continue;
			}
			result *= args[index];
			sets.add(args[index]);
		}
		return result;
	}
}
