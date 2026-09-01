
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SquareNums {

	public static int[] squareNums(int[] nums) {
		int[] squares = new int[nums.length];
		int array = 0;
		while (array < nums.length) {
			squares[array] = nums[array] * nums[array];
			array++;
		}
		return squares;
	}
}
