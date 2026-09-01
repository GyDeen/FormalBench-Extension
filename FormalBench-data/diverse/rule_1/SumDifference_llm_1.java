import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDifference {

	public static int sumDifference(int num) {
		int ret = 0;
		int total = 0;
		for (int j = 1; j <= num; j++) {
			ret += j;
			total += j * j;
		}
		int end = (int) Math.pow(ret, 2);
		return end - total;
	}
}
