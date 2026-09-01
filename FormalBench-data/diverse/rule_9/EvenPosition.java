
import java.io.*;
import java.lang.*;
import java.math.*;

class EvenPosition {

	public static Boolean evenPosition(int[] nums) {
		for (int i = 0; i < nums.length; i = i + 2) {
			if (nums[i] % 2 != 0) {
				return false;
			}
		}
		return true;
	}
}
