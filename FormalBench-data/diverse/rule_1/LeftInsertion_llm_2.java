
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LeftInsertion {

	public static int leftInsertion(int[] args, int array) {
		int result = 0;
		int top = args.length - 1;
		while (result <= top) {
			int i = (result + top) / 2;
			if (args[i] == array) {
				return i;
			} else if (args[i] < array) {
				result = i + 1;
			} else {
				top = i - 1;
			}
		}
		return result;
	}
}
