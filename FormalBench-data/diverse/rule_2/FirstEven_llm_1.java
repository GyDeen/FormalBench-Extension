
import java.io.*;
import java.lang.*;
import java.math.*;

class FirstEven {

	public static int firstEven(int[] nums) {
		int even = 0;
		int total = 0;
		while (total < nums.length) {
			if (nums[total] % 2 == 0) {
				even = nums[total];
				break;
			}
			total++;
		}
		return even;
	}
}
