
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivOfNums {

	public static int[] divOfNums(int[] nums, int Array, int c) {
		int count = 0;

		for (int i : nums) {
			if (i % Array == 0 && i % c == 0) {
				count++;
			}
		}

		int[] div = new int[count];
		int start = 0;

		for (int num : nums) {
			if (num % Array == 0 && num % c == 0) {
				div[start++] = num;
			}
		}

		return div;
	}
}
