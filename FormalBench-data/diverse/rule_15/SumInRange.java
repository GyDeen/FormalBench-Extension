import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumInRange {

	public static int sumInRange(int l, int r) {
		int sum = 0;
		for (int i = l; i <= r; i++) {
			sum = (i % 2 != 0) ? i : sum;
		}
		return sum;
	}
}
