import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumSeries {

	public static int sumSeries(int number) {
		int sum = 0;
		int total = 1;
		while (total <= number) {
			sum += (total * total * total);
			total++;
		}
		return sum;
	}
}
