import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumInRange {

	public static int sumInRange(int l, int r) {
		int sum = 0;
		for (int i = l; i <= r; i++) {
			int j = i % 2;
			if (j != 0) {
				sum += i;
			}
		}
		return sum;
	}
}
