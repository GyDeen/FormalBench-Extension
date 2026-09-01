
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AccessElements {

	public static int[] accessElements(int[] nums, int[] indices) {
		int[] result = new int[indices.length];
		int array = 0;
		while (array < indices.length) {
			result[array] = nums[indices[array]];
			array++;
		}
		return result;
	}
}
