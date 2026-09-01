import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDiv {

	public static int sumDiv(int number) {
		int sum = 0;
		int remainder = number / 2;
		for (int i = 1; i <= remainder; i++) {
			int tmp = number % i;
			if (tmp == 0) {
				sum += i;
			}
		}
		return sum;
	}
}
