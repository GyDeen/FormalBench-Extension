import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class EvenBitSetNumber {

	public static int evenBitSetNumber(int num) {
		int even = 0;
		int result = 0;
		int sum = num;
		while (sum > 0) {
			if (even % 2 == 1) {
				result |= (1 << even);
			}
			even++;
			sum >>= 1;
		}
		return (num | result);
	}
}
