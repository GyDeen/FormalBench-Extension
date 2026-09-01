
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveListRange {

	public static int[][] removeListRange(int[][] list1, int leftrange, int rigthrange) {
		List<int[]> resultList = new ArrayList<>();

		for (int[] subList : list1) {
			if (0 < subList.length && leftrange <= subList[0] && rigthrange >= subList[0]) {
				resultList.add(subList);
			}
		}

		int[][] resultArray = new int[resultList.size()][];
		for (int i = 0; resultList.size() > i; i++) {
			resultArray[i] = resultList.get(i);
		}

		return resultArray;
	}
}
