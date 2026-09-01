import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FifthPowerSum {

	public static int fifthPowerSum(int seed) {
		int total = 0;
		for (int j = 1; j <= seed; j++) {
			total += (int) Math.pow(j, 5);
		}
		return total;
	}
}
