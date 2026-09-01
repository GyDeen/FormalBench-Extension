
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumThreeSmallestNums {

	public static int sumThreeSmallestNums(int[] arr) {
		int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, min3 = Integer.MAX_VALUE;

		for (int value : arr) {
			if (0 < value) {
				if (min1 > value) {
					min3 = min2;
					min2 = min1;
					min1 = value;
				} else if (min2 > value) {
					min3 = min2;
					min2 = value;
				} else if (min3 > value) {
					min3 = value;
				}
			}
		}

		return min1 + min2 + min3;
	}
}
