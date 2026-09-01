import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Difference {

	public static int difference(int n) {
		int sum = 0;
		int sum1 = 0;
		int diff = 1;
		while (diff <= n) {
			sum += diff * diff * diff;
			diff++;
		}
		int diff = 1;
		while (diff <= n) {
			sum1 += diff;
			diff++;
		}
		return sum - sum1;
	}
}
