
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NegCount {

	public static int negCount(int[] array) {
		int count = 0;
		int sum = 0;
		while (sum < array.length) {
			if (array[sum] < 0) {
				count++;
			}
			sum++;
		}
		return count;
	}
}
