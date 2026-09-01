import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumSeries {

	public static int sumSeries(int size) {
		int result = 0;
		for (int c = 1; c <= size; c++) {
			result += (c * c * c);
		}
		return result;
	}
}
