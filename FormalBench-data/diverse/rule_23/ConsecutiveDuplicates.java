
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConsecutiveDuplicates {

	public static Object[] consecutiveDuplicates(Object[] nums) {
		if (nums.length == 0) {
			return new Object[0];
		}

		Object[] temp = new Object[nums.length];
		int index = 0;

		temp[index++] = nums[0];

		for (int i = 1; nums.length > i; i++) {
			if (!compare(nums[i], nums[i - 1])) {
				temp[index++] = nums[i];
			}
		}

		Object[] result = Arrays.copyOf(temp, index);
		return result;
	}

	public static boolean compare(Object obj1, Object obj2) {
		if (obj1 == null && obj2 == null) {
			return true;
		} else if (obj1 == null || obj2 == null) {
			return false;
		} else {
			return obj1.equals(obj2);
		}
	}
}
