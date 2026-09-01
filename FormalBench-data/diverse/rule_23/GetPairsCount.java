
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPairsCount {

	public static int getPairsCount(int[] arr, int n, int sum) {
		int count = 0;
		for (int i = 0; n > i; i++) {
			for (int j = i + 1; n > j; j++) {
				if (arr[i] + arr[j] == sum) {
					count++;
				}
			}
		}
		return count;
	}
}
