
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NegCount {

	public static int negCount(int[] array) {
		int count = 0;
		for (int i = 0; array.length > i; i++) {
			if (0 > array[i]) {
				count++;
			}
		}
		return count;
	}
}
