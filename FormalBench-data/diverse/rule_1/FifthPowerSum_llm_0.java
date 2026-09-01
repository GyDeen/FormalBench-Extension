import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FifthPowerSum {

	public static int fifthPowerSum(int seed) {
		int ret = 0;
		for (int j = 1; j <= seed; j++) {
			ret += (int) Math.pow(j, 5);
		}
		return ret;
	}
}
