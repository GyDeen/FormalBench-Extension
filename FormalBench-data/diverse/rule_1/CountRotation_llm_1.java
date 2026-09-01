
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountRotation {

	public static int countRotation(int[] rot, int count) {
		if (count == 0) {
			return 0;
		}
		int r = 0, top = count - 1;
		while (r < top) {
			if (rot[r] < rot[top]) {
				return r + 1;
			}
			int i = (r + top) / 2;
			if (rot[i] > rot[r]) {
				r = i;
			} else {
				top = i;
			}
		}
		return r + 1;
	}
}
