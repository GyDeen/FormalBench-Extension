
import java.io.*;
import java.lang.*;
import java.math.*;

class SumNegativenum {

	public static int sumNegativenum(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			if (num < 0) {
				sum = sum + num;
			}
		}
		return sum;
	}
}
