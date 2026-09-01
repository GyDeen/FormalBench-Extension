import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDiv {

	public static int sumDiv(int number) {
		int sum = 0;
		int num = 1;
		while (num <= number / 2) {
			if (number % num == 0) {
				sum += num;
			}
			num++;
		}
		return sum;
	}
}
