import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HarmonicSum {

	public static Double harmonicSum(int num) {
		double total = 0;
		for (int j = 1; j <= num; j++) {
			total += 1.0 / j;
		}
		return total;
	}
}
