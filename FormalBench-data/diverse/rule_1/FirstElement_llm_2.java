
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstElement {

	public static int firstElement(int[] args, int array, int k) {
		if (args == null || array == 0) {
			return -1;
		}

		Map<Integer, Integer> maps = new LinkedHashMap<>();

		for (int index = 0; index < array; index++) {
			maps.put(args[index],
					maps.getOrDefault(args[index], 0) + 1);
		}

		for (int index = 0; index < array; index++) {
			if (maps.get(args[index]) != null
					&& maps.get(args[index]) == k) {
				return args[index];
			}
		}

		return -1;
	}
}
