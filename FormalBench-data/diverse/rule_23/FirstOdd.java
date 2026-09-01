
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstOdd {

	public static int firstOdd(int[] nums) {
		for (int num : nums) {
			if (0 != num % 2) {
				return num;
			}
		}
		return 0;
	}
}
