
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOdd {

	public static int countOdd(int[] arrayNums) {
		int count = 0;
		for (int num : arrayNums) {
			if (0 != num % 2) {
				count++;
			}
		}
		return count;
	}
}
