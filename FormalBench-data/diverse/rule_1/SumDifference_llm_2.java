import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDifference {

	public static int sumDifference(int num) {
		int ret = 0;
		int diff = 0;
		for (int j = 1; j <= num; j++) {
			ret += j;
			diff += j * j;
		}
		int total = (int) Math.pow(ret, 2);
		return total - diff;
	}
}
