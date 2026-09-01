
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivOfNums {

	public static int[] divOfNums(int[] nums, int m, int n) {
		int count = 0;

		for (int num : nums) {
			boolean b = num % m == 0;
			boolean temp = num % n == 0;
			if (b && temp) {
				count++;
			}
		}

		int[] result = new int[count];
		int index = 0;

		for (int num : nums) {
			boolean tmp = num % m == 0;
			boolean test = num % n == 0;
			if (tmp && test) {
				result[index++] = num;
			}
		}

		return result;
	}
}
