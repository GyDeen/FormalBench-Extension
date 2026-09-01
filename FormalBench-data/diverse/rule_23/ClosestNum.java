import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ClosestNum {

	public static int closestNum(int n) {
		int diff = 0;
		int minDiff = Integer.MAX_VALUE;
		for (int i = 1; n > i; i++) {
			diff = Math.abs(n - i);
			if (minDiff > diff) {
				minDiff = diff;
			}
		}
		return n - minDiff;
	}
}
