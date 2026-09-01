
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumThreeSmallestNums {

	public static int sumThreeSmallestNums(int[] args) {
		int ret = Integer.MAX_VALUE, min = Integer.MAX_VALUE,
				max = Integer.MAX_VALUE;

		for (int n : args) {
			if (n > 0) {
				if (n < ret) {
					max = min;
					min = ret;
					ret = n;
				} else if (n < min) {
					max = min;
					min = n;
				} else if (n < max) {
					max = n;
				}
			}
		}

		return ret + min + max;
	}
}
