
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class RemoveListRange {

	public static int[][] removeListRange(int[][] list1, int leftrange, int rigthrange) {
		List<int[]> resultList = new ArrayList<>();

		for (int[] subList : list1) {
			if (!(subList.length > 0 && subList[0] >= leftrange && subList[0] <= rigthrange))
				;
			else {
				resultList.add(subList);
			}
		}

		int[][] resultArray = new int[resultList.size()][];
		for (int i = 0; i < resultList.size(); i++) {
			resultArray[i] = resultList.get(i);
		}

		return resultArray;
	}
}
