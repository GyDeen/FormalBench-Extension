import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Difference {

	public static int difference(int n) {
		int sum = 0;
		int sum1 = 0;
		int num = 1;
		while (num <= n) {
			sum += num * num * num;
			num++;
		}
		int num = 1;
		while (num <= n) {
			sum1 += num;
			num++;
		}
		return sum - sum1;
	}
}
