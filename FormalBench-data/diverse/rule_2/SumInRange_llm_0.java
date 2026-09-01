import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumInRange {

	public static int sumInRange(int l, int r) {
		int sum = 0;
		int total = l;
		while (total <= r) {
			if (total % 2 != 0) {
				sum += total;
			}
			total++;
		}
		return sum;
	}
}
