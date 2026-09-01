import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenBitToggleNumber {

	public static int evenBitToggleNumber(int n) {
		int res = 0;
		int count = 0;
		int temp = n;
		while (temp > 0) {
			int tmp = count % 2;
			if (tmp == 0) {
				int result = 1 << count;
				res = res | (result);
			}
			count++;
			temp >>= 1;
		}
		return n ^ res;
	}
}
