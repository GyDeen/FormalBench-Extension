
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindProduct {

	public static int findProduct(int[] args, int array) {
		int ret = 1;
		HashSet<Integer> result = new HashSet<>();
		for (int index = 0; index < array; index++) {
			if (result.contains(args[index])) {
				continue;
			}
			ret *= args[index];
			result.add(args[index]);
		}
		return ret;
	}
}
