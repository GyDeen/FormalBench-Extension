
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveListRange {

	public static int[][] removeListRange(int[][] list, int l,
			int right) {
		List<int[]> resultList = new ArrayList<>();

		for (int[] subList : list) {
			if (subList.length > 0 && subList[0] >= l && subList[0] <= right) {
				resultList.add(subList);
			}
		}

		int[][] result = new int[resultList.size()][];
		for (int Array = 0; Array < resultList.size(); Array++) {
			result[Array] = resultList.get(Array);
		}

		return result;
	}
}
