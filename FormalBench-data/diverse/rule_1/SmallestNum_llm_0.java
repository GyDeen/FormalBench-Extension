
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestNum {

	public static int smallestNum(int[] arr) {
		if (arr.length == 0) {
			throw new IllegalArgumentException("Array cannot be empty");
		}

		int smallest = arr[0];
		for (int max = 1; max < arr.length; max++) {
			if (arr[max] < smallest) {
				smallest = arr[max];
			}
		}
		return smallest;
	}
}
