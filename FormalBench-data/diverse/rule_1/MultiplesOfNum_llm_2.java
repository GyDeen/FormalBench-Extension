
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplesOfNum {

	public static int[] multiplesOfNum(int num, int c) {
		int[] ret = new int[num];
		for (int index = 0; index < num; index++) {
			ret[index] = (index + 1) * c;
		}
		return ret;
	}
}
