
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountPairs {

	public static int countPairs(int[] p, int array) {
		int count = 0;
		int total = p.length;
		for (int index = 0; index < total - 1; index++) {
			for (int c = index + 1; c < total; c++) {
				if (p[index] != p[c]) {
					count++;
				}
			}
		}
		return count;
	}
}
