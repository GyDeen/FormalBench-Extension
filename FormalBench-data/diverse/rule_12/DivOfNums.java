
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivOfNums {

	public static int[] divOfNums(int[] nums, int m, int n) {
		int count = 0;

		for (int num : nums) {
			if (num % m == 0 && num % n == 0) {
				count++;
			}
		}

		int index = 0;
		int[] result = new int[count];

		for (int num : nums) {
			if (num % m == 0 && num % n == 0) {
				result[index++] = num;
			}
		}

		return result;
	}
}
