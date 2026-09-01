
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NegCount {

	public static int negCount(int[] array) {
		int count = 0;
		int result = 0;
		while (result < array.length) {
			if (array[result] < 0) {
				count++;
			}
			result++;
		}
		return count;
	}
}
