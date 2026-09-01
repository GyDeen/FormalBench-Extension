import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sumoffactors {

	public static int sumoffactors(int n) {
		int sum = 0;
		for (int i = 2; i <= n; i++) {
			if (0 == n % i) {
				if (0 == i % 2)
					sum += i;
			}
		}
		return sum;
	}
}
