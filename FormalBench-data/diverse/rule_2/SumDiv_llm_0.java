import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDiv {

	public static int sumDiv(int number) {
		int sum = 0;
		int result = 1;
		while (result <= number / 2) {
			if (number % result == 0) {
				sum += result;
			}
			result++;
		}
		return sum;
	}
}
