
import java.io.*;
import java.lang.*;
import java.math.*;

class FirstEven {

	public static int firstEven(int[] nums) {
		int even = 0;
		int odd = 0;
		while (odd < nums.length) {
			if (nums[odd] % 2 == 0) {
				even = nums[odd];
				break;
			}
			odd++;
		}
		return even;
	}
}
