import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestMultiple {

	public static int smallestMultiple(int num) {
		if (num <= 2) {
			return num;
		}
		int smallest = num * 2;
		int r[] = new int[num];
		for (int f = 0; f < num; f++) {
			r[f] = f + 1;
		}
		while (true) {
			for (int k = 0; k < num; k++) {
				if (smallest % r[k] != 0) {
					smallest += num;
					break;
				}
				if (r[k] == r[num - 1]
						&& smallest % r[k] == 0) {
					return smallest;
				}
			}
		}
	}
}
