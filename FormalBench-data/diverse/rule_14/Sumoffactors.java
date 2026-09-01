import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sumoffactors {

	public static int sumoffactors(int n) {
		int sum = 0;
		for (int i = 2; i <= n; i++) {
			if (!(n % i == 0))
				;
			else {
				if (i % 2 == 0)
					sum += i;
			}
		}
		return sum;
	}
}
