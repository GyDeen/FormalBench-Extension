
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstElement {

	public static int firstElement(int[] array, int Array, int k) {
		if (array == null || Array == 0) {
			return -1;
		}

		Map<Integer, Integer> map = new LinkedHashMap<>();

		for (int index = 0; index < Array; index++) {
			map.put(array[index],
					map.getOrDefault(array[index], 0) + 1);
		}

		for (int index = 0; index < Array; index++) {
			if (map.get(array[index]) != null
					&& map.get(array[index]) == k) {
				return array[index];
			}
		}

		return -1;
	}
}
