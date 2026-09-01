
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountOdd {

	public static int countOdd(int[] args) {
		int result = 0;
		for (int i : args) {
			if (i % 2 != 0) {
				result++;
			}
		}
		return result;
	}
}
