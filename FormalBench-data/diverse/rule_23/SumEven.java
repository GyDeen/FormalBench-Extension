import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEven {

	public static int sumEven(int l, int r) {
		int sum = 0;
		for (int i = l; r >= i; i++) {
			if (i % 2 == 0) {
				sum += i;
			}
		}
		return sum;
	}
}
