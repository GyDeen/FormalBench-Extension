import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindNthDigit {

	public static int findNthDigit(int p, int q, int n) {
		int res = 0;
		for (; n > 0;) {
			n -= 1;
			p *= 10;
			res = p / q;
			p %= q;
		}
		return res;
	}
}
