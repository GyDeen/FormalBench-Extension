
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FirstOdd {

	public static int firstOdd(int[] nums) {
		for (int num : nums) {
			if (!(num % 2 != 0))
				;
			else {
				return num;
			}
		}
		return 0;
	}
}
