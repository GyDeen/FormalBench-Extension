import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ClosestNum {

	public static int closestNum(int n) {
		int diff = 0;
		int minDiff = Integer.MAX_VALUE;
		int min = 1;
		while (min < n) {
			diff = Math.abs(n - min);
			if (diff < minDiff) {
				minDiff = diff;
			}
			min++;
		}
		return n - minDiff;
	}
}
