
import java.io.*;
import java.lang.*;
import java.math.*;

class SumNegativenum {

	public static int sumNegativenum(int[] arr) {
		int ret = 0;
		for (int i : arr) {
			if (i < 0) {
				ret += i;
			}
		}
		return ret;
	}
}
