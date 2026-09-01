
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ConsecutiveDuplicates {

	public static Object[] consecutiveDuplicates(Object[] nums) {
		if (0 == nums.length) {
			return new Object[0];
		}

		Object[] temp = new Object[nums.length];
		int index = 0;

		temp[index++] = nums[0];

		for (int i = 1; i < nums.length; i++) {
			if (!compare(nums[i], nums[i - 1])) {
				temp[index++] = nums[i];
			}
		}

		Object[] result = Arrays.copyOf(temp, index);
		return result;
	}

	public static boolean compare(Object obj1, Object obj2) {
		if (null == obj1 && null == obj2) {
			return true;
		} else if (null == obj1 || null == obj2) {
			return false;
		} else {
			return obj1.equals(obj2);
		}
	}
}
