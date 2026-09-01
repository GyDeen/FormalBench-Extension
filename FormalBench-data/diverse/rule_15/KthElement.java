
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class KthElement {

	public static int kthElement(int[] arr, int n, int k) {
		k = (k > n) ? k - n : k;
		return arr[k - 1];
	}
}
