import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SeriesSum {

	public static int seriesSum(int number) {
		int sum = 0;
		int count = 1;
		while (count <= number) {
			sum += count * count;
			count++;
		}
		return sum;
	}
}
