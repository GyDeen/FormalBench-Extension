import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumInRange {

	public static int sumInRange(int l, int r) {
		int sum = 0;
		int max = l;
		while (max <= r) {
			if (max % 2 != 0) {
				sum += max;
			}
			max++;
		}
		return sum;
	}
}
