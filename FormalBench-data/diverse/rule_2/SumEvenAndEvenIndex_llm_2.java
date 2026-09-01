
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenAndEvenIndex {

	public static int sumEvenAndEvenIndex(int[] arr, int n) {
		int count = 0;
		int index = 0;
		while (index < n) {
			if (index % 2 == 0) {
				if (arr[index] % 2 == 0) {
					count += arr[index];
				}
			}
			index++;
		}
		return count;
	}
}
