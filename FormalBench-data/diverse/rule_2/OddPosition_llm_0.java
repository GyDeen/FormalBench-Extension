
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class OddPosition {

	public static Boolean oddPosition(int[] nums) {
		int n = nums.length;
		int p = 0;
		while (p < n) {
			if (nums[p] % 2 != 0) {
				return false;
			}
			p += 2;
		}
		return true;
	}
}
