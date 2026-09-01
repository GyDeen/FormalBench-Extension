
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractIndexList {

	public static int[] extractIndexList(int[] l1, int[] l2, int[] l3) {
		List<Integer> resultList = new ArrayList<>();
		int result = 0;
		while (result < l1.length) {
			if (l1[result] == l2[result] && l1[result] == l3[result]) {
				resultList.add(l1[result]);
			}
			result++;
		}
		return resultList.stream().mapToInt(Integer::intValue).toArray();
	}
}
