
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LeftInsertion {

	public static int leftInsertion(int[] args, int array) {
		int ret = 0;
		int top = args.length - 1;
		while (ret <= top) {
			int i = (ret + top) / 2;
			if (args[i] == array) {
				return i;
			} else if (args[i] < array) {
				ret = i + 1;
			} else {
				top = i - 1;
			}
		}
		return ret;
	}
}
