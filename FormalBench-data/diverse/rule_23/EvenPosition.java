
import java.io.*;
import java.lang.*;
import java.math.*;

class EvenPosition {

	public static Boolean evenPosition(int[] nums) {
		for (int i = 0; nums.length > i; i += 2) {
			if (0 != nums[i] % 2) {
				return false;
			}
		}
		return true;
	}
}
