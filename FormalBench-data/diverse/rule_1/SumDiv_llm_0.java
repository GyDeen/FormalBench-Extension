import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumDiv {

	public static int sumDiv(int num) {
		int result = 0;
		for (int c = 1; c <= num / 2; c++) {
			if (num % c == 0) {
				result += c;
			}
		}
		return result;
	}
}
