import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HarmonicSum {

	public static Double harmonicSum(int n) {
		double sum = 0;
		int start = 1;
		while (start <= n) {
			sum += 1.0 / start;
			start++;
		}
		return sum;
	}
}
