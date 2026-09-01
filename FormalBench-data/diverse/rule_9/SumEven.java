import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEven {

	public static int sumEven(int l, int r) {
		int sum = 0;
		for (int i = l; i <= r; i++) {
			if (i % 2 == 0) {
				sum = sum + i;
			}
		}
		return sum;
	}
}
