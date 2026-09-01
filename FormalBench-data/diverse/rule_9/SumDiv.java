import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDiv {

	public static int sumDiv(int number) {
		int sum = 0;
		for (int i = 1; i <= number / 2; i++) {
			if (number % i == 0) {
				sum = sum + i;
			}
		}
		return sum;
	}
}
