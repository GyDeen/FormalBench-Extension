import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEven {

	public static int sumEven(int num, int n) {
		int result = 0;
		for (int j = num; j <= n; j++) {
			if (j % 2 == 0) {
				result += j;
			}
		}
		return result;
	}
}
