import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ClosestNum {

	public static int closestNum(int n) {
		int diff = 0;
		int minDiff = Integer.MAX_VALUE;
		int max = 1;
		while (max < n) {
			diff = Math.abs(n - max);
			if (diff < minDiff) {
				minDiff = diff;
			}
			max++;
		}
		return n - minDiff;
	}
}
