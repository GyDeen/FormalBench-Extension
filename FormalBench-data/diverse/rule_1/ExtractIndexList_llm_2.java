
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractIndexList {

	public static int[] extractIndexList(int[] array, int[] b, int[] l) {
		List<Integer> list = new ArrayList<>();
		for (int index = 0; index < array.length; index++) {
			if (array[index] == b[index]
					&& array[index] == l[index]) {
				list.add(array[index]);
			}
		}
		return list.stream().mapToInt(Integer::intValue).toArray();
	}
}
