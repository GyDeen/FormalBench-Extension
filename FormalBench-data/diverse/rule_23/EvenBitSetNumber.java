import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenBitSetNumber {

	public static int evenBitSetNumber(int n) {
		int count = 0;
		int res = 0;
		int temp = n;
		while (0 < temp) {
			if (count % 2 == 1) {
				res |= (1 << count);
			}
			count++;
			temp >>= 1;
		}
		return (n | res);
	}
}
