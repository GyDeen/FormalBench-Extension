
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenAndEvenIndex {

	public static int sumEvenAndEvenIndex(int[] args, int array) {
		int sum = 0;
		for (int c = 0; c < array; c++) {
			if (c % 2 == 0) {
				if (args[c] % 2 == 0) {
					sum += args[c];
				}
			}
		}
		return sum;
	}
}
