
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class InterleaveLists {

	public static int[] interleaveLists(int[] list1, int[] list2, int[] list3) {
        int n1;
		n1 = list1.length
		int n2;
		n2 = list2.length
		int n3;
		n3 = list3.length
		int totalLength;
		totalLength = n1 + n2 + n3
		int[] result;
		result = new int[totalLength]
		int index;
		index = 0
		int k = 0;
		int j = 0;
		int i = 0;
		while (i < n1 && j < n2 && k < n3) {
            result[index++] = list1[i++];
            result[index++] = list2[j++];
            result[index++] = list3[k++];
        }

        while (i < n1) {
            result[index++] = list1[i++];
        }
        while (j < n2) {
            result[index++] = list2[j++];
        }
        while (k < n3) {
            result[index++] = list3[k++];
        }

        return result;
    }
}
