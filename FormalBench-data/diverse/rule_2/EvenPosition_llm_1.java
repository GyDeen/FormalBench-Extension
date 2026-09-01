
import java.io.*;
import java.lang.*;
import java.math.*;

class EvenPosition {

	public static Boolean evenPosition(int[] nums) {
		int even = 0;
		while (even < nums.length) {
			if (nums[even] % 2 != 0) {
				return false;
			}
			even += 2;
		}
		return true;
	}
}
