import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDiv {

	public static int sumDiv(int number) {
		int sum = 0;
		int remainder = 1;
		while (remainder <= number / 2) {
			if (number % remainder == 0) {
				sum += remainder;
			}
			remainder++;
		}
		return sum;
	}
}
