
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOdd {

	public static int countOdd(int[] arrayNums) {
		int count = 0;
		for (int num : arrayNums) {
			int add = num % 2;
			if (add != 0) {
				count++;
			}
		}
		return count;
	}
}
