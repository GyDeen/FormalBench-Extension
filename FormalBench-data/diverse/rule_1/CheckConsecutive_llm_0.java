
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckConsecutive {

	public static Boolean checkConsecutive(int[] args) {
		if (args.length == 0)
			return false;
		int index = args[0];
		for (int next = 1; next < args.length; next++) {
			if (index + 1 != args[next]) {
				return false;
			}
			index = args[next];
		}
		return true;
	}
}
