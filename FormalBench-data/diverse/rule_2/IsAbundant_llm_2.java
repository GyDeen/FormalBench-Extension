import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsAbundant {

	public static Boolean isAbundant(int n) {
		int sum = 0;
		int count = 1;
		while (count <= n / 2) {
			if (n % count == 0) {
				sum += count;
			}
			count++;
		}
		return sum > n;
	}
}
