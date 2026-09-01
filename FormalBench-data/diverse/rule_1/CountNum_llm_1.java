import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNum {

	public static int countNum(int code) {
		int result = 0;
		while (code > 0) {
			if ((code & 1) == 1) {
				result++;
			}
			code = code >> 1;
		}
		return result;
	}
}
