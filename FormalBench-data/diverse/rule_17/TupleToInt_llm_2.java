
import java.io.*;
import java.lang.*;
import java.math.*;

class TupleToInt {

	public static int tupleToInt(int[] nums) {
		int result = 0;
		for (int num : nums) {
			int tmp = result * 10;
			result = tmp + num;
		}
		return result;
	}
}
