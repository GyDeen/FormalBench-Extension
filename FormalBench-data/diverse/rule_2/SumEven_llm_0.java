import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEven {

	public static int sumEven(int l, int r) {
		int sum = 0;
		int total = l;
		while (total <= r) {
			if (total % 2 == 0) {
				sum += total;
			}
			total++;
		}
		return sum;
	}
}
