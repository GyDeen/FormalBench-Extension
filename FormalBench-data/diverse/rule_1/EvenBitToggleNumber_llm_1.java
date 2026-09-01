import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenBitToggleNumber {

	public static int evenBitToggleNumber(int number) {
		int i = 0;
		int ret = 0;
		int total = number;
		while (total > 0) {
			if (ret % 2 == 0) {
				i = i | (1 << ret);
			}
			ret++;
			total >>= 1;
		}
		return number ^ i;
	}
}
