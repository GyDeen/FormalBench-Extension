
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SumPositivenum {

	public static int sumPositivenum(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			if (num > 0) {
				sum = sum + num;
			}
		}
		return sum;
	}
}
