import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSetBits {

	public static int countSetBits(int num) {
		int ret = 0;
		for (int j = 1; j <= num; j++) {
			ret += Integer.bitCount(j);
		}
		return ret;
	}
}
