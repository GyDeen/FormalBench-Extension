
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FlattenList {

	public static int[] flattenList(Object[] objects) {
		List<Integer> l = new ArrayList<>();
		flattenHelper(objects, l);
		return l.stream().mapToInt(f -> f).toArray();
	}

	private static void flattenHelper(Object[] array, List<Integer> list) {
		for (Object object : array) {
			if (object instanceof Object[]) {
				flattenHelper((Object[]) object, list);
			} else {
				list.add((Integer) object);
			}
		}
	}
}
