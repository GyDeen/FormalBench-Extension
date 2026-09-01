
import java.io.*;
import java.lang.*;
import java.math.*;

class FirstEven {

	public static int firstEven(int[] nums) {
		int even = 0;
		for (int i = 0; i < nums.length; i++) {
			int next = nums[i] % 2;
			if (next == 0) {
				even = nums[i];
				break;
			}
		}
		return even;
	}
}
