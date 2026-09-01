import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SeriesSum {

	public static int seriesSum(int num) {
		int result = 0;
		for (int c = 1; c <= num; c++) {
			result += c * c;
		}
		return result;
	}
}
