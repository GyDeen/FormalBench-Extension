
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class KthElement {

	public static int kthElement(int[] args, int array, int c) {
		if (c > array) {
			c = c - array;
		}
		return args[c - 1];
	}
}
