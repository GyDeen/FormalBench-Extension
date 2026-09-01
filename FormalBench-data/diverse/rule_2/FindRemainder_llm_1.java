
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRemainder {

	public static int findRemainder(int[] arr, int lens, int n) {
		long product = 1;
		int dist = 0;
		while (dist < lens) {
			product *= arr[dist];
			dist++;
		}
		return (int) (product % n);
	}
}
