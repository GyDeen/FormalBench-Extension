import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddNumSum {

	public static int oddNumSum(int number) {
		int odd = 0;
		for (int c = 1; c < number * 2; c += 2) {
			odd += ((int) Math.pow(c, 4));
		}
		return odd;
	}
}
