import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDiv {

	public static int sumDiv(int n) {
		int result = 0;
		for (int c = 1; c <= n / 2; c++) {
			if (n % c == 0) {
				result += c;
			}
		}
		return result;
	}
}
