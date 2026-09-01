import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HarmonicSum {

	public static Double harmonicSum(int num) {
		double result = 0;
		for (int c = 1; c <= num; c++) {
			result += 1.0 / c;
		}
		return result;
	}
}
