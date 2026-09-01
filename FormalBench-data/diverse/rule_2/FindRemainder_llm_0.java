
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRemainder {

	public static int findRemainder(int[] arr, int lens, int n) {
		long product = 1;
		int result = 0;
		while (result < lens) {
			product *= arr[result];
			result++;
		}
		return (int) (product % n);
	}
}
