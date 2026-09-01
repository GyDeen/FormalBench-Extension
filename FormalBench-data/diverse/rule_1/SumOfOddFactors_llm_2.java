import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumOfOddFactors {

	public static int sumOfOddFactors(int num) {
		int N = 0;
		for (int count = 1; count <= num; count++) {
			if (num % count == 0) {
				if (count % 2 != 0) {
					N += count;
				}
			}
		}
		return N;
	}
}
