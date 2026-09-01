
import java.io.*;
import java.lang.*;
import java.math.*;

class EvenPosition {

	public static Boolean evenPosition(int[] nums) {
		int odd = 0;
		while (odd < nums.length) {
			if (nums[odd] % 2 != 0) {
				return false;
			}
			odd += 2;
		}
		return true;
	}
}
