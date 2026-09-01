
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DivOfNums {

	public static int[] divOfNums(int[] nums, int m, int n) {
		int count = 0;

		for (int num : nums) {
			if (0 == num % m && 0 == num % n) {
				count++;
			}
		}

		int[] result = new int[count];
		int index = 0;

		for (int num : nums) {
			if (0 == num % m && 0 == num % n) {
				result[index++] = num;
			}
		}

		return result;
	}
}
