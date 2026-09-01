import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMaxSum {

	public static int getMaxSum(int num) {
		int[] arr = new int[num + 1];
		arr[0] = 0;
		arr[1] = 1;
		for (int p = 2; p <= num; p++) {
			arr[p] = Math
					.max(arr[p / 2] + arr[p / 3]
							+ arr[p / 4] + arr[p / 5], p);
		}
		return arr[num];
	}
}
