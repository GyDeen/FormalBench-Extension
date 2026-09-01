import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfSeries {

	public static int sumOfSeries(int size) {
		int ret = 0;
		for (int j = 1; j <= size; j++) {
			ret += (j * j * j);
		}
		return ret;
	}
}
