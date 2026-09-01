import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ClosestNum {

	public static int closestNum(int n) {
		int diff = 0;
		int minDiff = Integer.MAX_VALUE;
		int closest = 1;
		while (closest < n) {
			diff = Math.abs(n - closest);
			if (diff < minDiff) {
				minDiff = diff;
			}
			closest++;
		}
		return n - minDiff;
	}
}
