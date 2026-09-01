
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class KthElement {

	public static int kthElement(int[] params, int array, int p) {
		if (p > array) {
			p = p - array;
		}
		return params[p - 1];
	}
}
