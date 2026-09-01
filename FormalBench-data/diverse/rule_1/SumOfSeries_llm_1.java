import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfSeries {

	public static int sumOfSeries(int size) {
		int result = 0;
		for (int c = 1; c <= size; c++) {
			result += (c * c * c);
		}
		return result;
	}
}
