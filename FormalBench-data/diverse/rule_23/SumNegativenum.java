
import java.io.*;
import java.lang.*;
import java.math.*;

class SumNegativenum {

	public static int sumNegativenum(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			if (0 > num) {
				sum += num;
			}
		}
		return sum;
	}
}
