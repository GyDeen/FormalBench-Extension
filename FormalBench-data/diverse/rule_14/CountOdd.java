
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOdd {

	public static int countOdd(int[] arrayNums) {
		int count = 0;
		for (int num : arrayNums) {
			if (!(num % 2 != 0))
				;
			else {
				count++;
			}
		}
		return count;
	}
}
