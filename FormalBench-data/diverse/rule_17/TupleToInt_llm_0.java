
import java.io.*;
import java.lang.*;
import java.math.*;

class TupleToInt {

	public static int tupleToInt(int[] nums) {
		int result = 0;
		for (int num : nums) {
			int i = result * 10;
			result = i + num;
		}
		return result;
	}
}
