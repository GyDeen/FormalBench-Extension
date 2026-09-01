
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumThreeSmallestNums {

	public static int sumThreeSmallestNums(int[] arr) {
		int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, min3 = Integer.MAX_VALUE;

		for (int value : arr) {
			if (value > 0) {
				if (value < min1) {
					min3 = min2;
					min2 = min1;
					min1 = value;
				} else if (value < min2) {
					min3 = min2;
					min2 = value;
				} else
					min3 = (value < min3) ? value : min3;
			}
		}

		return min1 + min2 + min3;
	}
}
