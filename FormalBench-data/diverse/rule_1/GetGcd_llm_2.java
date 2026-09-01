
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetGcd {

	public static int getGcd(int[] arg) {
		int g = 0;
		int ret = arg[0];
		for (int j = 1; j < arg.length; j++) {
			if (arg[j] > ret) {
				g = g > ret ? g : ret;
			} else {
				ret = arg[j];
			}
		}
		return g;
	}
}
