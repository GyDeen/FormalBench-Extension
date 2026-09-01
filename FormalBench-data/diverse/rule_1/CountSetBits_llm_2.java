import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSetBits {

	public static int countSetBits(int size) {
		int ret = 0;
		for (int c = 1; c <= size; c++) {
			ret += Integer.bitCount(c);
		}
		return ret;
	}
}
