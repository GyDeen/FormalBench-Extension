import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ClosestNum {

	public static int closestNum(int num) {
		int best = 0;
		int closest = Integer.MAX_VALUE;
		for (int j = 1; j < num; j++) {
			best = Math.abs(num - j);
			if (best < closest) {
				closest = best;
			}
		}
		return num - closest;
	}
}
