
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractIndexList {

	public static int[] extractIndexList(int[] l1, int[] l2, int[] l3) {
		List<Integer> resultList = new ArrayList<>();
		for (int i = 0; i < l1.length; i++) {
			if (l1[i] == l2[i]) {
				if (l1[i] == l3[i]) {
					resultList.add(l1[i]);
				}
			}
		}
		return resultList.stream().mapToInt(Integer::intValue).toArray();
	}
}
