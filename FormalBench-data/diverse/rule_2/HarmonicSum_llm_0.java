import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HarmonicSum {

	public static Double harmonicSum(int n) {
		double sum = 0;
		int i = 1;
		while (i <= n) {
			sum += 1.0 / i;
			i++;
		}
		return sum;
	}
}
