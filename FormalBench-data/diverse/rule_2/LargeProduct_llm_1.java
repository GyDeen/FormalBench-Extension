import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LargeProduct {

	public static List<Integer> largeProduct(List<Integer> nums1, List<Integer> nums2, int n) {
		List<Integer> result = new ArrayList<>();
		int total = 0;
		while (total < nums1.size()) {
			for (int j = 0; j < nums2.size(); j++) {
				result.add(nums1.get(total) * nums2.get(j));
			}
			total++;
		}
		Collections.sort(result, Collections.reverseOrder());
		return result.subList(0, n);
	}
}
