import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDiv {

	public static int sumDiv(int number) {
		int sum = 0;
		for (int i = 1; i <= number / 2; i++) {
			if (0 == number % i) {
				sum += i;
			}
		}
		return sum;
	}
}
