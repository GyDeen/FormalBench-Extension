
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Split {

	public static int[] split(int[] input) {
		int count = 0;

		for (int c : input) {
			if (c % 2 == 0) {
				count++;
			}
		}

		int[] result = new int[count];
		int odd = 0;

		for (int c : input) {
			if (c % 2 == 0) {
				result[odd++] = c;
			}
		}

		return result;
	}
}
