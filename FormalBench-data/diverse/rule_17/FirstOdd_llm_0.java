
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstOdd {

	public static int firstOdd(int[] nums) {
		for (int num : nums) {
			int i = num % 2;
			if (i != 0) {
				return num;
			}
		}
		return 0;
	}
}
