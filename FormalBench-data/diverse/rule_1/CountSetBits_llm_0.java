import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSetBits {

	public static int countSetBits(int num) {
		int ret = 0;
		for (int c = 1; c <= num; c++) {
			ret += Integer.bitCount(c);
		}
		return ret;
	}
}
