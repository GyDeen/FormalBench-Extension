
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Solve {

	public static Boolean solve(int[] args, int Array) {
		int result = 0;
		for (int index = 0; index < Array; index++) {
			for (int c = index + 1; c < Array; c++) {
				if (args[index] > args[c]) {
					result++;
				}
			}
		}
		return result <= 1;
	}
}
