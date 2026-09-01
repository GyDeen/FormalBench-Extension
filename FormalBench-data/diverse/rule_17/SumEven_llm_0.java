import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEven {

	public static int sumEven(int l, int r) {
		int sum = 0;
		for (int i = l; i <= r; i++) {
			int tmp = i % 2;
			if (tmp == 0) {
				sum += i;
			}
		}
		return sum;
	}
}
