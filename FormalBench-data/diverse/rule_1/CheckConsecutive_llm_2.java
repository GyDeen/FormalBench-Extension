
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckConsecutive {

	public static Boolean checkConsecutive(int[] args) {
		if (args.length == 0)
			return false;
		int index = args[0];
		for (int n = 1; n < args.length; n++) {
			if (index + 1 != args[n]) {
				return false;
			}
			index = args[n];
		}
		return true;
	}
}
