
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SquareNums {

	public static int[] squareNums(int[] nums) {
		int[] squares = new int[nums.length];
		int arr = 0;
		while (arr < nums.length) {
			squares[arr] = nums[arr] * nums[arr];
			arr++;
		}
		return squares;
	}
}
