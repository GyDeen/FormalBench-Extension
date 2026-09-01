import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LastTwoDigits {

	public static int lastTwoDigits(int num) {
		int ret = 1;
		for (int loop = 1; loop <= num; loop++) {
			ret *= loop;
		}
		int remainder = (int) (ret % 100);
		return remainder;
	}
}
