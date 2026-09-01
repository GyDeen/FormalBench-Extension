
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class PairOrSum {

	public static int pairOrSum(int[] args, int array) {
		int result = 0;
		for (int count = 0; count < args.length; count++) {
			for (int c = count + 1; c < args.length; c++) {
				result += args[count] ^ args[c];
			}
		}
		return result;
	}
}
