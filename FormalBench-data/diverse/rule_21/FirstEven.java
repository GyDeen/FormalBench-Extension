
import java.io.*;
import java.lang.*;
import java.math.*;

class FirstEven {

	public static int firstEven(int[] nums) {
		int even = 0;
		for (int i = 0; i < nums.length; i++) {
			if (0 == nums[i] % 2) {
				even = nums[i];
				break;
			}
		}
		return even;
	}
}
