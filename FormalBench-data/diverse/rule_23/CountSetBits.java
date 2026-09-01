import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSetBits {

	public static int countSetBits(int n) {
		int count = 0;
		for (int i = 1; n >= i; i++) {
			count += Integer.bitCount(i);
		}
		return count;
	}
}
