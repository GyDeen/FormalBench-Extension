import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumSeries {

	public static int sumSeries(int number) {
		int sum = 0;
		int count = 1;
		while (count <= number) {
			sum += (count * count * count);
			count++;
		}
		return sum;
	}
}
