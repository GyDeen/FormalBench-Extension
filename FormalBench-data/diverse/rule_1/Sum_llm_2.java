import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sum {

	public static int sum(int size) {
		int[] arr = new int[size + 1];
		arr[0] = 0;
		arr[1] = 0;

		for (int p = 2; p <= size; p++) {
			if (arr[p] == 0) {
				for (int c = p; c <= size; c += p) {
					arr[c] += p;
				}
			}
		}

		return arr[size];
	}
}
