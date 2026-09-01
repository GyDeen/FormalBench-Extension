
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplyArray {

	public static int multiplyList(int[] arr) {
		int ret = 1;
		for (int i : arr) {
			ret *= i;
		}
		return ret;
	}
}
