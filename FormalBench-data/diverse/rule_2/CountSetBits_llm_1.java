import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountSetBits {

	public static int countSetBits(int n) {
		int count = 0;
		int offset = 1;
		while (offset <= n) {
			count += Integer.bitCount(offset);
			offset++;
		}
		return count;
	}
}
