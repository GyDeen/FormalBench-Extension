
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractIndexList {

	public static int[] extractIndexList(int[] l1, int[] l2, int[] l3) {
		List<Integer> resultList = new ArrayList<>();
		int index = 0;
		while (index < l1.length) {
			if (l1[index] == l2[index] && l1[index] == l3[index]) {
				resultList.add(l1[index]);
			}
			index++;
		}
		return resultList.stream().mapToInt(Integer::intValue).toArray();
	}
}
