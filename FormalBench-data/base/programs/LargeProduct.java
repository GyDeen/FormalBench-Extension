import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class LargeProduct {
    
    public static List<Integer> largeProduct(List<Integer> nums1, List<Integer> nums2, int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums1.size(); i++) {
            for (int j = 0; j < nums2.size(); j++) {
                result.add(nums1.get(i) * nums2.get(j));
            }
        }
        Collections.sort(result, Collections.reverseOrder());
        return result.subList(0, n);
    }
}

