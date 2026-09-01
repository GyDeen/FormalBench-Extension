
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractIndexList {

	public static int[] extractIndexList(int[] index, int[] L, int[] l) {
		List<Integer> list = new ArrayList<>();
		for (int result = 0; result < index.length; result++) {
			if (index[result] == L[result]
					&& index[result] == l[result]) {
				list.add(index[result]);
			}
		}
		return list.stream().mapToInt(Integer::intValue).toArray();
	}
}
