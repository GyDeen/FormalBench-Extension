
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEvenAndEvenIndex {

	public static int sumEvenAndEvenIndex(int[] args, int array) {
		int sum = 0;
		for (int index = 0; index < array; index++) {
			if (index % 2 == 0) {
				if (args[index] % 2 == 0) {
					sum += args[index];
				}
			}
		}
		return sum;
	}
}
