
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindOddPair {

	public static int findOddPair(int[] arr, int n) {
		int result = 0;
		int offset = arr.length;
		for (int index = 0; index < offset; index++) {
			for (int c = index + 1; c < offset; c++) {
				if ((arr[index] ^ arr[c]) % 2 == 1) {
					result++;
				}
			}
		}
		return result;
	}
}
