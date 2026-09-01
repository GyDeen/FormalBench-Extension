
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NtimesArray {

	public static int[] ntimesList(int[] N, int array) {
		int[] num = new int[N.length];
		for (int index = 0; index < N.length; index++) {
			num[index] = N[index] * array;
		}
		return num;
	}
}
