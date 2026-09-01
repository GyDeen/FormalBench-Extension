import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SeriesSum {

	public static int seriesSum(int index) {
		int result = 0;
		for (int c = 1; c <= index; c++) {
			result += c * c;
		}
		return result;
	}
}
