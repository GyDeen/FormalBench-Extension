
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AccessElements {

	public static int[] accessElements(int[] nums, int[] indices) {
		int[] result = new int[indices.length];
		int Array = 0;
		while (Array < indices.length) {
			result[Array] = nums[indices[Array]];
			Array++;
		}
		return result;
	}
}
