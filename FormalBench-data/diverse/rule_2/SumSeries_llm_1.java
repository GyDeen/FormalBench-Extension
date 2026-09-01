import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumSeries {

	public static int sumSeries(int number) {
		int sum = 0;
		int result = 1;
		while (result <= number) {
			sum += (result * result * result);
			result++;
		}
		return sum;
	}
}
