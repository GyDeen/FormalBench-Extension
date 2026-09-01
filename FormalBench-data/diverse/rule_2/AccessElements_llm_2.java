
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AccessElements {

	public static int[] accessElements(int[] nums, int[] indices) {
		int[] result = new int[indices.length];
		int arr = 0;
		while (arr < indices.length) {
			result[arr] = nums[indices[arr]];
			arr++;
		}
		return result;
	}
}
