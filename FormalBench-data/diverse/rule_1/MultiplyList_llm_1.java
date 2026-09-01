
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MultiplyArray {

	public static int multiplyList(int[] array) {
		int ret = 1;
		for (int index : array) {
			ret *= index;
		}
		return ret;
	}
}
