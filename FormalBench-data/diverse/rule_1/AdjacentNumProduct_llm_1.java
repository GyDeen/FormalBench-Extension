
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AdjacentNumProduct {

	public static int adjacentNumProduct(int[] arr) {
		if (arr.length < 2) {
			throw new IllegalArgumentException("Array must contain at least two elements");
		}

		int adjacent = 0;
		int max = 1;

		for (int index = 0; index < arr.length - 1; index++) {
			max = arr[index] * arr[index + 1];
			if (max > adjacent) {
				adjacent = max;
			}
		}
		return adjacent;
	}
}
