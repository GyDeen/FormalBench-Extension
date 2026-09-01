
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckConsecutive {

	public static Boolean checkConsecutive(int[] values) {
		if (values.length == 0)
			return false;
		int index = values[0];
		for (int next = 1; next < values.length; next++) {
			if (index + 1 != values[next]) {
				return false;
			}
			index = values[next];
		}
		return true;
	}
}
