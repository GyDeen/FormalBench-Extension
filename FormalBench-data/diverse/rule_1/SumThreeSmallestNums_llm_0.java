
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumThreeSmallestNums {

	public static int sumThreeSmallestNums(int[] args) {
		int ret = Integer.MAX_VALUE, min = Integer.MAX_VALUE,
				max = Integer.MAX_VALUE;

		for (int i : args) {
			if (i > 0) {
				if (i < ret) {
					max = min;
					min = ret;
					ret = i;
				} else if (i < min) {
					max = min;
					min = i;
				} else if (i < max) {
					max = i;
				}
			}
		}

		return ret + min + max;
	}
}
