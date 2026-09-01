import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenBitSetNumber {

	public static int evenBitSetNumber(int n) {
		int res = 0;
		int count = 0;
		int temp = n;
		while (temp > 0) {
			if (count % 2 == 1) {
				res |= (1 << count);
			}
			temp >>= 1;
			count++;
		}
		return (n | res);
	}
}
