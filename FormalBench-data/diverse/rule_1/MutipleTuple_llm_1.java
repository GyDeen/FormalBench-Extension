
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MutipleTuple {

	public static int mutipleTuple(int[] arr) {
		int ret = 1;
		for (int prod : arr) {
			ret *= prod;
		}
		return ret;
	}
}
