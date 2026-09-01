import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumEven {

	public static int sumEven(int num, int b) {
		int ret = 0;
		for (int j = num; j <= b; j++) {
			if (j % 2 == 0) {
				ret += j;
			}
		}
		return ret;
	}
}
