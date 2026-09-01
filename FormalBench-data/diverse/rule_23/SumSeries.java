import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumSeries {

	public static int sumSeries(int number) {
		int sum = 0;
		for (int i = 1; number >= i; i++) {
			sum += (i * i * i);
		}
		return sum;
	}
}
