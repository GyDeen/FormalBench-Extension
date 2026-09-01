
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetGcd {

	public static int getGcd(int[] args) {
		int ret = 0;
		int tmp = args[0];
		for (int j = 1; j < args.length; j++) {
			if (args[j] > tmp) {
				ret = ret > tmp ? ret : tmp;
			} else {
				tmp = args[j];
			}
		}
		return ret;
	}
}
