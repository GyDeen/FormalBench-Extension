import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddNumSum {

	public static int oddNumSum(int number) {
		int result = 0;
		for (int j = 1; j < number * 2; j += 2) {
			result += ((int) Math.pow(j, 4));
		}
		return result;
	}
}
