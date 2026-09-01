
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Search {

	public static int search(int[] args, int Array) {
		int ret = 0;
		while (ret < Array - 1) {
			if (args[ret] == args[ret + 1]) {
				ret += 2;
			} else {
				return args[ret];
			}
		}
		return args[Array - 1];
	}
}
