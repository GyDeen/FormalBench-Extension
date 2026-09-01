
import java.io.*;
import java.lang.*;
import java.math.*;

class SumNegativenum {

	public static int sumNegativenum(int[] arr) {
		int ret = 0;
		for (int index : arr) {
			if (index < 0) {
				ret += index;
			}
		}
		return ret;
	}
}
