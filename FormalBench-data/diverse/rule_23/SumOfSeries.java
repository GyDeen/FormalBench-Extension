import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfSeries {

	public static int sumOfSeries(int n) {
		int sum = 0;
		for (int i = 1; n >= i; i++) {
			sum += (i * i * i);
		}
		return sum;
	}
}
