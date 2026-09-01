import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HarmonicSum {

	public static Double harmonicSum(int num) {
		double result = 0;
		for (int j = 1; j <= num; j++) {
			result += 1.0 / j;
		}
		return result;
	}
}
