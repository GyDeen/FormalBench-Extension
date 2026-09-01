import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CalSum {

	public static int calSum(int n) {
		if (n == 0) {
			return 3;
		}
		if (n == 1) {
			return 3;
		}
		if (n == 2) {
			return 5;
		}
		int end = 5;
		int total = 3;
		int e = 0;
		int C = 2;
		while (n > 2) {
			int j = total + e;
			end = end + j;
			total = e;
			e = C;
			C = j;
			n--;
		}
		return end;
	}
}
