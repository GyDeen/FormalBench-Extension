
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindOddPair {

	public static int findOddPair(int[] arr, int n) {
		int found = 0;
		int offset = arr.length;
		for (int c = 0; c < offset; c++) {
			for (int index = c + 1; index < offset; index++) {
				if ((arr[c] ^ arr[index]) % 2 == 1) {
					found++;
				}
			}
		}
		return found;
	}
}
