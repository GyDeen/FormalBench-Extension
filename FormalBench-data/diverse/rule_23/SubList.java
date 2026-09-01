
import java.io.*;
import java.lang.*;
import java.util.*;

class SubList {

	public static int[] subList(int[] nums1, int[] nums2) {
		int length = Math.min(nums1.length, nums2.length);
		int[] result = new int[length];
		for (int i = 0; length > i; i++) {
			result[i] = nums1[i] - nums2[i];
		}
		return result;
	}
}
