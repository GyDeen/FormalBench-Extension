
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FindRemainder {

	public static int findRemainder(int[] args, int array, int mask) {
		long l = 1;
		for (int index = 0; index < array; index++) {
			l *= args[index];
		}
		return (int) (l % mask);
	}
}
