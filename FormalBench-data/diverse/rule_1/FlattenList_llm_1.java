
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FlattenList {

	public static int[] flattenList(Object[] objects) {
		List<Integer> list = new ArrayList<>();
		flattenHelper(objects, list);
		return list.stream().mapToInt(f -> f).toArray();
	}

	private static void flattenHelper(Object[] array, List<Integer> lists) {
		for (Object object : array) {
			if (object instanceof Object[]) {
				flattenHelper((Object[]) object, lists);
			} else {
				lists.add((Integer) object);
			}
		}
	}
}
