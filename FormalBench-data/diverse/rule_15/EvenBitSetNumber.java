import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenBitSetNumber {

	public static int evenBitSetNumber(int n) {
		int count = 0;
		int res = 0;
		int temp = n;
		while (temp > 0) {
			res = (count % 2 == 1) ? (1 << count) : res;
			count++;
			temp >>= 1;
		}
		return (n | res);
	}
}
