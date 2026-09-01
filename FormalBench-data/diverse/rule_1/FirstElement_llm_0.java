
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstElement {

	public static int firstElement(int[] args, int array, int k) {
		if (args == null || array == 0) {
			return -1;
		}

		Map<Integer, Integer> map = new LinkedHashMap<>();

		for (int index = 0; index < array; index++) {
			map.put(args[index],
					map.getOrDefault(args[index], 0) + 1);
		}

		for (int index = 0; index < array; index++) {
			if (map.get(args[index]) != null
					&& map.get(args[index]) == k) {
				return args[index];
			}
		}

		return -1;
	}
}
