import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NextPowerOf2 {

	public static int nextPowerOf2(int num) {
		if (num == 0) {
			return 1;
		}
		int result = 1;
		while (result < num) {
			result = result << 1;
		}
		return result;
	}
}
