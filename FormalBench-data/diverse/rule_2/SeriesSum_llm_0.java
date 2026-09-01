import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SeriesSum {

	public static int seriesSum(int number) {
		int sum = 0;
		int total = 1;
		while (total <= number) {
			sum += total * total;
			total++;
		}
		return sum;
	}
}
