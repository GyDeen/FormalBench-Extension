import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HarmonicSum {

	public static Double harmonicSum(int n) {
		double sum = 0;
		int end = 1;
		while (end <= n) {
			sum += 1.0 / end;
			end++;
		}
		return sum;
	}
}
