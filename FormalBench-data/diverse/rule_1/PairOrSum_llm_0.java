
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PairOrSum {

	public static int pairOrSum(int[] pairs, int array) {
		int result = 0;
		for (int count = 0; count < pairs.length; count++) {
			for (int c = count + 1; c < pairs.length; c++) {
				result += pairs[count] ^ pairs[c];
			}
		}
		return result;
	}
}
