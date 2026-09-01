
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetGcd {

	public static int getGcd(int[] args) {
		int g = 0;
		int ret = args[0];
		for (int j = 1; j < args.length; j++) {
			if (args[j] > ret) {
				g = g > ret ? g : ret;
			} else {
				ret = args[j];
			}
		}
		return g;
	}
}
