import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumInRange {

	public static int sumInRange(int l, int r) {
		int sum = 0;
		for (int i = l; r >= i; i++) {
			if (0 != i % 2) {
				sum += i;
			}
		}
		return sum;
	}
}
