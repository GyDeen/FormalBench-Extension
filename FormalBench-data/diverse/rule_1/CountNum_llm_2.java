import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CountNum {

	public static int countNum(int number) {
		int ret = 0;
		while (number > 0) {
			if ((number & 1) == 1) {
				ret++;
			}
			number = number >> 1;
		}
		return ret;
	}
}
