import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Difference {

	public static int difference(int n) {
		int sum = 0;
		int sum1 = 0;
		int count = 1;
		while (count <= n) {
			sum += count * count * count;
			count++;
		}
		int count = 1;
		while (count <= n) {
			sum1 += count;
			count++;
		}
		return sum - sum1;
	}
}
