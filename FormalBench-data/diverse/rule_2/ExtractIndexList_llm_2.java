
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ExtractIndexList {

	public static int[] extractIndexList(int[] l1, int[] l2, int[] l3) {
		List<Integer> resultList = new ArrayList<>();
		int ret = 0;
		while (ret < l1.length) {
			if (l1[ret] == l2[ret] && l1[ret] == l3[ret]) {
				resultList.add(l1[ret]);
			}
			ret++;
		}
		return resultList.stream().mapToInt(Integer::intValue).toArray();
	}
}
