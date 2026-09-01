import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SmallestMultiple {

	public static int smallestMultiple(int size) {
		if (size <= 2) {
			return size;
		}
		int smallest = size * 2;
		int r[] = new int[size];
		for (int f = 0; f < size; f++) {
			r[f] = f + 1;
		}
		while (true) {
			for (int k = 0; k < size; k++) {
				if (smallest % r[k] != 0) {
					smallest += size;
					break;
				}
				if (r[k] == r[size - 1]
						&& smallest % r[k] == 0) {
					return smallest;
				}
			}
		}
	}
}
