
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
		for (int x = 1; x < arr.length; x++) {
			if (arr[x] < smallest) {
				smallest = arr[x];
			}
		}
		return smallest;
	}
}
