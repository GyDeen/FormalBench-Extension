
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class KthElement {

	public static int kthElement(int[] args, int array, int p) {
		if (p > array) {
			p = p - array;
		}
		return args[p - 1];
	}
}
