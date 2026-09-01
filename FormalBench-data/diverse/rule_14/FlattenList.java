
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FlattenList {

	public static int[] flattenList(Object[] list1) {
		List<Integer> tempList = new ArrayList<>();
		flattenHelper(list1, tempList);
		return tempList.stream().mapToInt(i -> i).toArray();
	}

	private static void flattenHelper(Object[] array, List<Integer> result) {
		for (Object obj : array) {
			if (!(obj instanceof Object[])) {
				result.add((Integer) obj);
			} else {
				flattenHelper((Object[]) obj, result);
			}
		}
	}
}
