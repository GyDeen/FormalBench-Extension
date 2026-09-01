import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMaxSum {

	public static int getMaxSum(int size) {
		int[] arr = new int[size + 1];
		arr[0] = 0;
		arr[1] = 1;
		for (int index = 2; index <= size; index++) {
			arr[index] = Math
					.max(arr[index / 2] + arr[index / 3]
							+ arr[index / 4] + arr[index / 5], index);
		}
		return arr[size];
	}
}
