
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddPosition {

	public static Boolean oddPosition(int[] nums) {
		int n = nums.length;
		for (int i = 0; n > i; i += 2) {
			if (0 != nums[i] % 2) {
				return false;
			}
		}
		return true;
	}
}
