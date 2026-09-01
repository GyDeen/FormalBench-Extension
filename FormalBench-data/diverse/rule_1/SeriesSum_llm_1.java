import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SeriesSum {

	public static int seriesSum(int num) {
		int value = 0;
		for (int c = 1; c <= num; c++) {
			value += c * c;
		}
		return value;
	}
}
