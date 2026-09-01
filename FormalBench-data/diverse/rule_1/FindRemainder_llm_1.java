
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRemainder {

	public static int findRemainder(int[] array, int num, int mask) {
		long l = 1;
		for (int index = 0; index < num; index++) {
			l *= array[index];
		}
		return (int) (l % mask);
	}
}
