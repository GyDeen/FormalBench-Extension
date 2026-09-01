
import java.io.*;
import java.lang.*;
import java.math.*;

class FirstEven {

	public static int firstEven(int[] nums) {
		int even = 0;
		int last = 0;
		while (last < nums.length) {
			if (nums[last] % 2 == 0) {
				even = nums[last];
				break;
			}
			last++;
		}
		return even;
	}
}
