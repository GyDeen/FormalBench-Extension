
import java.io.*;
import java.lang.*;
import java.math.*;

class EvenPosition {

	public static Boolean evenPosition(int[] nums) {
		int result = 0;
		while (result < nums.length) {
			if (nums[result] % 2 != 0) {
				return false;
			}
			result += 2;
		}
		return true;
	}
}
