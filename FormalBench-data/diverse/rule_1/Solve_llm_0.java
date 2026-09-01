
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Solve {

	public static Boolean solve(int[] args, int Array) {
		int result = 0;
		for (int c = 0; c < Array; c++) {
			for (int k = c + 1; k < Array; k++) {
				if (args[c] > args[k]) {
					result++;
				}
			}
		}
		return result <= 1;
	}
}
