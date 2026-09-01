import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FifthPowerSum {

	public static int fifthPowerSum(int number) {
		int seed = 0;
		for (int j = 1; j <= number; j++) {
			seed += (int) Math.pow(j, 5);
		}
		return seed;
	}
}
