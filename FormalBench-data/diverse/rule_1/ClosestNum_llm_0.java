import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ClosestNum {

	public static int closestNum(int num) {
		int closest = 0;
		int max = Integer.MAX_VALUE;
		for (int j = 1; j < num; j++) {
			closest = Math.abs(num - j);
			if (closest < max) {
				max = closest;
			}
		}
		return num - max;
	}
}
