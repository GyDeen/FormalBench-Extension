
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MutipleTuple {

	public static int mutipleTuple(int[] nums) {
		int product = 1;
		for (int num : nums) {
			product = product * num;
		}
		return product;
	}
}
