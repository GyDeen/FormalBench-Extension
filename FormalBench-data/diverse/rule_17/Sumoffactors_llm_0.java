import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sumoffactors {

	public static int sumoffactors(int n) {
		int sum = 0;
		for (int i = 2; i <= n; i++) {
			int j = n % i;
			if (j == 0) {
				int tmp = i % 2;
				if (tmp == 0)
					sum += i;
			}
		}
		return sum;
	}
}
