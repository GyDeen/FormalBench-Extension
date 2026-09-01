
import java.io.*;
import java.lang.*;
import java.util.*;

class SubList {

	public static int[] subList(int[] nums1, int[] nums2) {
		int length = Math.min(nums1.length, nums2.length);
		int[] result = new int[length];
		int sub = 0;
		while (sub < length) {
			result[sub] = nums1[sub] - nums2[sub];
			sub++;
		}
		return result;
	}
}
