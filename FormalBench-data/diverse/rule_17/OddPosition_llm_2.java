
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddPosition {

	public static Boolean oddPosition(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < n; i += 2) {
			int num = nums[i] % 2;
			if (num != 0) {
				return false;
			}
		}
		return true;
	}
}
