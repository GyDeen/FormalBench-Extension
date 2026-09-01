
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NegCount {

	public static int negCount(int[] array) {
		int count = 0;
		int max = 0;
		while (max < array.length) {
			if (array[max] < 0) {
				count++;
			}
			max++;
		}
		return count;
	}
}
