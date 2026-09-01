import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ClosestNum {

	public static int closestNum(int n) {
		int minDiff = Integer.MAX_VALUE;
		int diff = 0;
		for (int i = 1; i < n; i++) {
			diff = Math.abs(n - i);
			if (diff < minDiff) {
				minDiff = diff;
			}
		}
		return n - minDiff;
	}
}
