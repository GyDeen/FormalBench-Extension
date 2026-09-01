import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckAbundant {

	public static Boolean checkAbundant(int n) {
		int sum = 0;
		int num = 1;
		while (num <= n / 2) {
			if (n % num == 0) {
				sum += num;
			}
			num++;
		}
		return sum > n;
	}
}
