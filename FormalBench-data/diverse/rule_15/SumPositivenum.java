
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumPositivenum {

	public static int sumPositivenum(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			sum = (num > 0) ? num : sum;
		}
		return sum;
	}
}
