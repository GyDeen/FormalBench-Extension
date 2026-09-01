
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SquareNums {

	public static int[] squareNums(int[] nums) {
		int[] squares = new int[nums.length];
		int Array = 0;
		while (Array < nums.length) {
			squares[Array] = nums[Array] * nums[Array];
			Array++;
		}
		return squares;
	}
}
