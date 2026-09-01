
import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class SequentialSearch {

	public static Object[] sequentialSearch(int[] args, int Array) {
		int i = 0;
		while (i < args.length
				&& args[i] != Array) {
			i++;
		}
		if (i < args.length) {
			return new Object[] { true, i };
		} else {
			return new Object[] { false };
		}
	}
}
