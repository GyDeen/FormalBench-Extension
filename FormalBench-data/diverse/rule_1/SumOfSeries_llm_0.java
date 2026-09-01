import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfSeries {

	public static int sumOfSeries(int size) {
		int ret = 0;
		for (int c = 1; c <= size; c++) {
			ret += (c * c * c);
		}
		return ret;
	}
}
