import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindNthDigit {

	public static int findNthDigit(int p, int q, int n) {
		int res = 0;
		while (n > 0) {
			p *= 10;
			n -= 1;
			res = p / q;
			p %= q;
		}
		return res;
	}
}
