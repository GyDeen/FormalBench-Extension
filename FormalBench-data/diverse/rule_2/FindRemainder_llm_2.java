
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRemainder {

	public static int findRemainder(int[] arr, int lens, int n) {
		long product = 1;
		int prod = 0;
		while (prod < lens) {
			product *= arr[prod];
			prod++;
		}
		return (int) (product % n);
	}
}
