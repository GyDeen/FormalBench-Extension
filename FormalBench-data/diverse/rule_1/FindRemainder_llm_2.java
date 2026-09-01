
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRemainder {

	public static int findRemainder(int[] values, int array, int mask) {
		long l = 1;
		for (int index = 0; index < array; index++) {
			l *= values[index];
		}
		return (int) (l % mask);
	}
}
