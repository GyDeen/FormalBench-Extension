import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sumoffactors {

	public static int sumoffactors(int n) {
		int sum = 0;
		for (int i = 2; i <= n; i++) {
			int tmp = n % i;
			if (tmp == 0) {
				int j = i % 2;
				if (j == 0)
					sum += i;
			}
		}
		return sum;
	}
}
