import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetMinSquares {

	public static int getMinSquares(int num) {
		int[] arr = new int[num + 1];
		arr[0] = 0;
		arr[1] = 1;
		for (int d = 2; d <= num; d++) {
			arr[d] = Integer.MAX_VALUE;
			for (int c = 1; c * c <= d; c++) {
				arr[d] = Math.min(arr[d],
						arr[d - c * c] + 1);
			}
		}
		return arr[num];
	}
}
